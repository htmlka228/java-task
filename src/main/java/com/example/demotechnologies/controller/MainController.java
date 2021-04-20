package com.example.demotechnologies.controller;

import com.example.demotechnologies.entity.Admin;
import com.example.demotechnologies.entity.User;
import com.example.demotechnologies.service.AdminService;
import com.example.demotechnologies.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MainController {

    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/users")
    public List<User> getUsers(){
        log.info("Return Sorted users by email");
        return userService.getUsers();
    }

    @GetMapping("/admins")
    public List<Admin> getAdmin() {
        log.info("Return Sorted admins by email");

        return adminService.getAdmins();
    }

    @GetMapping("user/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("admin/{id}")
    public Admin getAdmin(@PathVariable Long id){
        return adminService.getAdminById(id);
    }

    @PostMapping("user/add")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping("admin/add")
    public Admin addAdmin(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    @PutMapping("user/change/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User user){
        return userService.save(user, id);
    }

    @PutMapping("admin/change/{id}")
    public Admin putAdmin(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.save(admin, id);
    }

    @PatchMapping("user/change/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user){
        return userService.save(user, id);
    }

    @PatchMapping("admin/change/{id}")
    public Admin patchAdmin(@PathVariable Long id, @RequestBody Admin admin){
        return adminService.save(admin, id);
    }

    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @DeleteMapping("admin/delete/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminService.deleteAdminById(id);
    }
}