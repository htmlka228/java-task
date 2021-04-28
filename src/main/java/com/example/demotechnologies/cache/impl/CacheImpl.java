package com.example.demotechnologies.cache.impl;

import com.example.demotechnologies.cache.Cache;
import com.example.demotechnologies.entity.AbstractEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
@RequiredArgsConstructor
@Getter
@Setter
public class CacheImpl<T extends AbstractEntity> implements Cache<T> {
    private Map<Long, T> cache;
}
