package com.example.demotechnologies.controller;

import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<Admin> getAdmin() {
        log.info("Return Sorted admins by email");

        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Long id){
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    @PutMapping("/{id}")
    public Admin putAdmin(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.save(admin, id);
    }

    @PatchMapping("/{id}")
    public Admin patchAdmin(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.save(admin, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }
}
