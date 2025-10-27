package com.linfeng.spring1910.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.linfeng.spring1910.mapper.ArticleMapper;
import com.linfeng.spring1910.pojo.Article;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.server.ArticleService;
import com.linfeng.spring1910.utils.JwtUtil;
import com.linfeng.spring1910.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/all")
    public Result<List<Article>> home(){
        return Result.success(articleService.getAll());
    }
    @GetMapping("/get")
    public Result<Article> get(@RequestParam int id){
        Article article = articleService.getArticle(id);
        if(article==null){
            return Result.error("Article doesn't exist");
        }else{
            return Result.success(article);
        }
    }
    @GetMapping("/my")
    public Result my() {
        Map<String,?> claims = ThreadLocalUtil.get();
        return Result.success(articleService.getArticleByUser(claims.get("username").toString()));
    }
    @PostMapping("/update")
    public Result update(String content, String title,@RequestParam int id){
        Map<String,?> claims = ThreadLocalUtil.get();
        Article article = articleService.getArticle(id);
        article.content = content;
        article.title = title;
        article.timestamp = String.valueOf(System.currentTimeMillis());
        if(claims.get("username").equals(article.username)){
            articleService.update(article);
            System.out.println(article.id);
            return Result.success();
        }else{
            return Result.error("Unauthorized");
        }
    }

}
