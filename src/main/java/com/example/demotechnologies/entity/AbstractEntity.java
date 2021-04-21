package com.example.demotechnologies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AbstractEntity implements Comparable<AbstractEntity>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Override
    public int compareTo(AbstractEntity o) {
        return getEmail().compareTo(o.getEmail());
    }
}
