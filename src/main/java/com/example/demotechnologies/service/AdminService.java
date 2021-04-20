package com.example.demotechnologies.service;

import com.example.demotechnologies.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAdmins();
    Admin getAdminById(Long id);
    Admin save(Admin admin);
    Admin save(Admin admin, Long id);
    void deleteAdminById(Long id);
}
