package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.cache.impl.CacheImpl;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdminServiceImplTest {

    @Mock
    AdminRepository adminRepository;

    @Mock
    CacheImpl<Admin> cache;

    @InjectMocks
    AdminServiceImpl adminService;

    @Test
    void getAdmins() {
        List<Admin> testAdmins = new ArrayList<>();
        testAdmins.add(Admin.builder().id(1L).email("Email").password("Pass").active(true).build());
        testAdmins.add(Admin.builder().id(2L).email("Find").password("Pass").active(true).build());
        testAdmins.add(Admin.builder().id(3L).email("Key").password("Pass").active(true).build());

        Map<Long, Admin> cacheAdmins = testAdmins.stream().collect(Collectors.toMap(AbstractEntity::getId, admin -> admin));


        Mockito.when(adminRepository.findAll()).thenReturn(testAdmins);
        Mockito.when(cache.getCache()).thenReturn(cacheAdmins);

        assertNotNull(adminService.getAdmins(), "Admin getting test");
        assertEquals(adminService.getAdmins(), testAdmins.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList()));
    }
}
