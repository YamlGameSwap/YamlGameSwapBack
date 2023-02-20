package com.yamlgameswap.back.service.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j(topic = "service.RedisService")
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setMap(String key, Map<String, String> map) {
        redisTemplate.opsForValue().set(key, map);
    }

    public String getString(String name) {
        return (String) (redisTemplate.opsForValue().get(name));
    }

    public String getResponseMsg(String language, String key) {
        return getString(language, key);
    }

    private String getString(String key, String name) {
        return getMap(key).get(name);
    }

    private Map<String, String> getMap(String key) {
        return ((Map<String, String>) redisTemplate.opsForValue().get(key));
    }

    public boolean setStringTime(String key, String value, int time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
