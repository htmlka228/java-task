package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.cache.Cache;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.exception.UserNotFoundException;
import com.example.demotechnologies.repository.UserRepository;
import com.example.demotechnologies.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Cache<User> userCache;

    @Override
    public List<User> getUsers() {
        log.info("Find and getting users from DB");

        try {
            List<User> users = userRepository.findAll();
            userCache.setCache(users);
            log.info("Sorting users by email");

            return users.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList());

        } catch (CannotCreateTransactionException e) {
            log.error("DB connection is failed, data loaded from cache");
            return userCache.getCache();
        }

    }

    @Override
    public User getUserById(Long id) {
        try {
            User user = userRepository.findUserById(id).orElse(null);

            if (user == null) {
                log.error("User not found");
                throw new UserNotFoundException("User not found");
            }

            return user;

        } catch (CannotCreateTransactionException e) {
            log.error(e + " Data loaded from cache");

            return userCache.getCache().stream()
                    .filter(el -> el.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(User user, Long id) {
        User saveUser = getUserById(id);

        if (saveUser == null) {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }

        saveUser.setId(id);
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(user.getPassword());
        saveUser.setActive(user.isActive());

        return userRepository.save(saveUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
