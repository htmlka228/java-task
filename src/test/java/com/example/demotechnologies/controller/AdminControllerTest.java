package com.example.demotechnologies.controller;

import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.service.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdminControllerTest {
    @Mock
    AdminService adminService;

    @InjectMocks
    AdminController mainController;

    @Test
    void getAdmin() {
        Long id = 1L;

        List<Admin> admins = new ArrayList<>();
        admins.add(Admin.builder().id(1L).email("Email").password("Pass").active(true).build());
        admins.add(Admin.builder().id(2L).email("Email").password("Pass").active(true).build());
        admins.add(Admin.builder().id(3L).email("Email").password("Pass").active(true).build());

        Mockito.when(adminService.getAdminById(id)).thenReturn(admins.get(id.intValue() - 1));
        assertNotNull(mainController.getAdmin(id));
    }
}
