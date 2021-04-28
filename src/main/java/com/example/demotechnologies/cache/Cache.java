package com.example.demotechnologies.cache;

import com.example.demotechnologies.entity.AbstractEntity;

import java.util.Map;

public interface Cache<T extends AbstractEntity> {
    Map<Long, T> getCache();
    void setCache(Map<Long, T> map);
}
