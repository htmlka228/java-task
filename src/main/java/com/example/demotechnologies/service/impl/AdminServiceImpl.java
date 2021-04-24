package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.caxhe.Cache;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.exception.AdminNotFoundException;
import com.example.demotechnologies.repository.AdminRepository;
import com.example.demotechnologies.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final Cache<Admin> adminCache;

    @Override
    public List<Admin> getAdmins() {
        log.info("Find and getting admins from DB");

        try {
            List<Admin> admins = adminRepository.findAll();
            log.info("Sorting admins by email");
            adminCache.setCache(admins);

            return admins.stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList());

        } catch (Exception e) {
            log.error(e + " Data loaded from cache");

            return adminCache.getCache();
        }
    }

    @Override
    public Admin getAdminById(Long id) {
        Admin admin = adminRepository.findAdminById(id).orElse(null);

        if (admin == null){
            log.error("Admin not found");
            throw new AdminNotFoundException("Admin not found");
        }

        return admin;
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin save(Admin admin, Long id) {
        Admin saveAdmin = getAdminById(id);
        if (saveAdmin == null) {
            log.error("Admin not found");
            throw new AdminNotFoundException("Admin not found");
        }

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
