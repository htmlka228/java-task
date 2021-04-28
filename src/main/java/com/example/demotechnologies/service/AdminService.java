package com.example.demotechnologies.service;

import com.example.demotechnologies.entity.Admin;

import java.util.Collection;

public interface AdminService {
    Collection<Admin> getAdmins();
    Admin getAdminById(Long id);
    Admin save(Admin admin);
    Admin save(Admin admin, Long id);
    void deleteAdminById(Long id);
    void update();
}
