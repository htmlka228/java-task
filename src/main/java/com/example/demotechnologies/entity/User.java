package com.example.demotechnologies.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity{
    @NotNull
    private boolean active;

    @Builder
    public User(Long id, String email, String password, boolean active) {
        super(id, email, password);
        this.active = active;
    }
}
