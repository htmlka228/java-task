package com.example.demotechnologies.service.Impl;

import com.example.demotechnologies.caxhe.UserCache;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.exception.UserNotFoundException;
import com.example.demotechnologies.repository.UserRepository;
import com.example.demotechnologies.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCache userCache;

    @Override
    public List<User> getUsers() {
        log.info("Find and getting users from DB");

        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javatest","postgres","pass");
        } catch (SQLException throwables) {
            log.error("DB connection is failed, data loaded from cache");
            return userCache.getUsers();
        }

        List<User> users = userRepository.findAll();
        userCache.setUsers(users);
        log.info("Sorting users by email");

        return users.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findUserById(id).orElse(null);

        if(user != null){
            return user;
        }
        else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(User user, Long id) {
        User saveUser = getUserById(id);
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
