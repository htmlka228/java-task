package com.example.demotechnologies.controller;

import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.service.AdminService;
import com.example.demotechnologies.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainControllerTest {
    @Mock
    UserService userService;

    @Mock
    AdminService adminService;

    @Test
    void getUser() {
        Long id = 2L;

        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).email("Email").password("Pass").active(true).build());
        users.add(User.builder().id(2L).email("Email").password("Pass").active(true).build());
        users.add(User.builder().id(3L).email("Email").password("Pass").active(true).build());

        Mockito.when(userService.getUserById(id)).thenReturn(users.get(id.intValue() - 1));

        assertNotNull(userService.getUserById(id));
    }

    @Test
    void getAdmin() {
        Long id = 1L;

        List<Admin> admins = new ArrayList<>();
        admins.add(Admin.builder().id(1L).email("Email").password("Pass").active(true).build());
        admins.add(Admin.builder().id(2L).email("Email").password("Pass").active(true).build());
        admins.add(Admin.builder().id(3L).email("Email").password("Pass").active(true).build());

        Mockito.when(adminService.getAdminById(id)).thenReturn(admins.get(id.intValue() - 1));
        assertNotNull(adminService.getAdminById(id));
    }
}