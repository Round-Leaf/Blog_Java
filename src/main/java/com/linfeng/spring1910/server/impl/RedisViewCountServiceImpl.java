package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.server.RedisViewCountService;
import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisViewCountServiceImpl implements RedisViewCountService {
    private final RedisTemplate<String,Object> redisTemplate;
    @Getter
    private String KEY_PREFIX = "article_view_count:";

    public RedisViewCountServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Long getCurrentCount(Integer articleId) {
        return Long.valueOf(redisTemplate.opsForValue().get(KEY_PREFIX + articleId).toString());
    }
    @Override
    public java.util.Set<String> getAllPageKeys() {
        return redisTemplate.keys(KEY_PREFIX + "*");
    }
    @Override
    public Long incrementCount(Integer articleId) {
       return redisTemplate.opsForValue().increment(KEY_PREFIX + articleId);
    }

    @Override
    public void resetCount(Integer articleId) {
        redisTemplate.opsForValue().set(KEY_PREFIX + articleId, 0);
    }

}
