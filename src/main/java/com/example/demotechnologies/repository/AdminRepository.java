package com.example.demotechnologies.repository;

import com.example.demotechnologies.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminById(Long id);
    void deleteById(@NonNull Long id);
}
