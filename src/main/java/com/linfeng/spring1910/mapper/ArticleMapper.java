package com.linfeng.spring1910.mapper;

import com.linfeng.spring1910.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("SELECT * FROM articles")
    public List<Article> getAll();

    @Update("UPDATE articles SET title=#{title},content=#{content},timestamp=#{timestamp},imageUrl=#{imageUrl} WHERE id=#{id}")
    public void update(Article article);

    @Select("SELECT * FROM articles WHERE id=#{id}")
    public Article getArticle(int id);

    @Select("SELECT * FROM articles WHERE username=#{username}")
    public List<Article> getArticleByUser(String username);

    @Select("INSERT INTO articles (title,content,timestamp,username,imageUrl) VALUES (#{title},#{content},#{timestamp},#{username},#{imageUrl})")
    void add(Article article);

    @Update("UPDATE articles SET views=views+${addedViews} WHERE id=#{id}")
    void addView(Integer id,Long addedViews);
}
