package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.server.PageStatService;
import com.linfeng.spring1910.server.RedisViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageStatServiceImpl implements PageStatService {
    @Autowired
    RedisViewCountService redisViewCountService;
    @Override
    public void incrementView(Integer articleId) {

        redisViewCountService.incrementCount(articleId);
        //System.out.println(redisViewCountService.getCurrentCount(articleId));
    }
}
