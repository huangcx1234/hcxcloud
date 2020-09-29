package com.jiurong.hcx.third.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description Mybatis Redis 二级缓存
 */
@Slf4j
public class MybatisRedisCache implements Cache {
    private String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> redisTemplate;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        log.info("Redis Cache id " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        getRedisTemplate();
        if (value != null) {
            redisTemplate.opsForValue().set(key.toString(), value, 30, TimeUnit.DAYS);
        }
    }

    @Override
    public Object getObject(Object key) {
        getRedisTemplate();
        try {
            if (key != null) {
                return redisTemplate.opsForValue().get(key.toString());
            }
        } catch (Exception e) {
            log.error("Redis Exception");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        getRedisTemplate();
        try {
            if (key != null) {
                redisTemplate.delete(key.toString());
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate();
        log.debug("清空缓存");
        try {
            Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public int getSize() {
        getRedisTemplate();
        Long size = redisTemplate.execute((RedisCallback<Long>) connection -> connection.dbSize());
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private void getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = SpringContextHolder.getBean("redisTemplate");
        }
    }
}
