package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.cache.impl.CacheImpl;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.exception.AdminNotFoundException;
import com.example.demotechnologies.exception.UserNotFoundException;
import com.example.demotechnologies.repository.UserRepository;
import com.example.demotechnologies.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CacheImpl<User> userCache;

    @Override
    public Collection<User> getUsers() {
        log.info("Find and getting users from DB");

        return userCache.getCache().values().stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        if (userCache.getCache().containsKey(id)) {
            return userCache.getCache().get(id);
        }

        try {
            User user = userRepository.findUserById(id).orElse(null);

            if (user == null) {
                log.error("Admin not found");
                throw new AdminNotFoundException("Admin not found");
            }

            return user;

        } catch (DataAccessResourceFailureException e) {
            log.error(e + " Data loaded from cache");

            if (!userCache.getCache().containsKey(id)) {
                throw new AdminNotFoundException("Admin not found");
            }

            return userCache.getCache().get(id);
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

    @Override
    @Scheduled(fixedRate = 20000)
    public void update() {
        try {
            userCache.setCache(userRepository.findAll().stream().collect(Collectors.toMap(AbstractEntity::getId, user -> user)));
        } catch (Exception e) {
            log.info("DB is not working");
        }
    }
}
