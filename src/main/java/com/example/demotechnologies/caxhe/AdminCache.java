package com.example.demotechnologies.caxhe;

import com.example.demotechnologies.entity.Admin;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class AdminCache {
    private List<Admin> admins;
}
