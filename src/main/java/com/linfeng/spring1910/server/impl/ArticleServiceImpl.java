package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.mapper.ArticleMapper;
import com.linfeng.spring1910.pojo.Article;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.server.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public void update(Article article) {
        articleMapper.update(article);
    }
    public Article getArticle(int id){
        return articleMapper.getArticle(id);
    }

    @Override
    public List<Article> getAll() {
        return articleMapper.getAll();
    }
}
