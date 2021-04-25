package com.example.demotechnologies.cache;

import com.example.demotechnologies.entity.AbstractEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Cache<T extends AbstractEntity> {
    private List<T> cache;
}
