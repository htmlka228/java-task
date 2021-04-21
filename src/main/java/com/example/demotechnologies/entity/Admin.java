package com.example.demotechnologies.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin extends AbstractEntity{
    private LocalDateTime lastLoginDate;
    private boolean active;

    @Builder
    public Admin(Long id, String email, String password, LocalDateTime lastLoginDate, boolean active) {
        super(id, email, password);
        this.lastLoginDate = lastLoginDate;
        this.active = active;
    }
}
