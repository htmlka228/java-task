package com.example.demotechnologies.controller;

import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController mainController;

    @Test
    void getUser() {
        Long id = 2L;

        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).email("Email").password("Pass").active(true).build());
        users.add(User.builder().id(2L).email("Email").password("Pass").active(true).build());
        users.add(User.builder().id(3L).email("Email").password("Pass").active(true).build());

        Mockito.when(userService.getUserById(id)).thenReturn(users.get(id.intValue() - 1));

        assertNotNull(mainController.getUser(id));
    }
}
