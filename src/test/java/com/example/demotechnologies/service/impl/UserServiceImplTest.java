package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.cache.Cache;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.repository.AdminRepository;
import com.example.demotechnologies.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    Cache<User> cache;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getUsers() {
        List<User> testUsers = new ArrayList<>();
        testUsers.add(User.builder().id(1L).email("Email").password("Pass").active(true).build());
        testUsers.add(User.builder().id(2L).email("Find").password("Pass").active(true).build());
        testUsers.add(User.builder().id(3L).email("Key").password("Pass").active(true).build());

        List<User> cacheAdmins = new ArrayList<>();
        testUsers.add(User.builder().id(1L).email("Find").password("Pass").active(true).build());
        testUsers.add(User.builder().id(2L).email("Email").password("Pass").active(true).build());
        testUsers.add(User.builder().id(3L).email("Key").password("Pass").active(true).build());


        Mockito.when(userRepository.findAll()).thenReturn(testUsers);
        Mockito.when(cache.getCache()).thenReturn(cacheAdmins);

        assertNotNull(userService.getUsers(), "Admin getting test");
        assertEquals(userService.getUsers(), testUsers.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList()));
        assertNotEquals(cache.getCache(), testUsers.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList()));
    }
}
