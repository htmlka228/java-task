package com.example.demotechnologies.service;

import com.example.demotechnologies.entity.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getUsers();
    User getUserById(Long id);
    User save(User user);
    User save(User user, Long id);
    void deleteUserById(Long id);
    void update();
}
