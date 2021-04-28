package com.example.demotechnologies.service.impl;

import com.example.demotechnologies.cache.Cache;
import com.example.demotechnologies.entity.AbstractEntity;
import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.exception.AdminNotFoundException;
import com.example.demotechnologies.repository.AdminRepository;
import com.example.demotechnologies.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final Cache<Admin> adminCache;

    @Override
    public Collection<Admin> getAdmins() {
        log.info("Find and getting admins from DB");

        return adminCache.getCache().values().stream().sorted(Comparator.comparing(AbstractEntity::getEmail)).collect(Collectors.toList());
    }

    @Override
    public Admin getAdminById(Long id) {
        if (adminCache.getCache().containsKey(id)) {
            return adminCache.getCache().get(id);
        }

        try {
            Admin admin = adminRepository.findAdminById(id).orElse(null);

            if (admin == null) {
                log.error("Admin not found");
                throw new AdminNotFoundException("Admin not found");
            }

            return admin;

        } catch (DataAccessResourceFailureException e) {
            log.error(e + " Data loaded from cache");

            if (!adminCache.getCache().containsKey(id)) {
                throw new AdminNotFoundException("Admin not found");
            }

            return adminCache.getCache().get(id);
        }
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

    @Override
    @Scheduled(fixedRate = 20000)
    public void update() {
        try {
            adminCache.setCache(adminRepository.findAll().stream().collect(Collectors.toMap(AbstractEntity::getId, admin -> admin)));
        } catch (Exception e) {
            log.info("DB is not working");
        }
    }
}
