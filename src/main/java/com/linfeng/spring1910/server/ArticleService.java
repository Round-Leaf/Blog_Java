package com.linfeng.spring1910.server;

import com.linfeng.spring1910.pojo.Article;
import com.linfeng.spring1910.pojo.Result;

import java.util.List;

public interface ArticleService {
    public void update(Article article);
    public Article getArticle(int id);
    List<Article> getArticleByUser(String username);
    List<Article> getAll();
}
