package com.example.demotechnologies.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin extends AbstractEntity{
    @NotNull
    private LocalDateTime lastLoginDate;

    @NotNull
    private boolean active;

    @Builder
    public Admin(Long id, String email, String password, LocalDateTime lastLoginDate, boolean active) {
        super(id, email, password);
        this.lastLoginDate = lastLoginDate;
        this.active = active;
    }
}
