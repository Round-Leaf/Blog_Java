package com.linfeng.spring1910.server;

public interface RedisViewCountService {
    public Long getCurrentCount(Integer articleId);

    java.util.Set<String> getAllPageKeys();
    String getKEY_PREFIX();
    public Long incrementCount(Integer articleId);
    public void resetCount(Integer articleId);
    //public RedisViewCountService(RedisTemplate<String, String> redisTemplate);

}
