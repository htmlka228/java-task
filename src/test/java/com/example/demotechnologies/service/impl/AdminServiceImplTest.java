package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Test
    void getAdmins() {
        List<Admin> testAdmins = adminService.getAdmins();
        assertNotNull(testAdmins, "Admin getting test");
        assertEquals(testAdmins, testAdmins.stream().sorted().collect(Collectors.toList()));
    }
}