package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getUsers() {
        List<User> testUsers = userService.getUsers();
        assertNotNull(testUsers, "User getting test");
        assertEquals(testUsers, testUsers.stream().sorted().collect(Collectors.toList()));
    }
}
