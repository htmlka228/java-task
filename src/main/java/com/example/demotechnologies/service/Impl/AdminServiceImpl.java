package com.example.demotechnologies.service.Impl;

import com.example.demotechnologies.caxhe.AdminCache;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.exception.AdminNotFoundException;
import com.example.demotechnologies.repository.AdminRepository;
import com.example.demotechnologies.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminCache adminCache;

    @Override
    public List<Admin> getAdmins() {
        log.debug("Find and getting admins from DB");

        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/javatest","postgres","pass");
        } catch (SQLException throwables) {
            log.error("DB connection is failed, data loaded from cache");
            return adminCache.getAdmins();
        }

        List<Admin> admins = adminRepository.findAll();
        log.trace("Sorting admins by email");
        adminCache.setAdmins(admins);
        Collections.sort(admins);

        return admins;
    }

    @Override
    public Admin getAdminById(Long id) {
        Admin admin = adminRepository.findAdminById(id).orElse(null);
        if(admin != null)
            return admin;
        else {
            throw new AdminNotFoundException("Admin not found");
        }
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin save(Admin admin, Long id) {
        Admin saveAdmin = getAdminById(id);
        saveAdmin.setId(id);
        saveAdmin.setEmail(admin.getEmail());
        saveAdmin.setPassword(admin.getPassword());
        saveAdmin.setLastLoginDate(admin.getLastLoginDate());
        saveAdmin.setActive(admin.isActive());

        return adminRepository.save(saveAdmin);
    }

    @Override
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}
