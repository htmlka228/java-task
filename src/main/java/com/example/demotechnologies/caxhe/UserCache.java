package com.example.demotechnologies.caxhe;

import com.example.demotechnologies.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UserCache {
    private List<User> users;
}
