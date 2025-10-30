package com.linfeng.spring1910.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.linfeng.spring1910.mapper.ArticleMapper;
import com.linfeng.spring1910.pojo.Article;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.server.ArticleService;
import com.linfeng.spring1910.server.PageStatService;
import com.linfeng.spring1910.utils.JwtUtil;
import com.linfeng.spring1910.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private PageStatService pageStatService;
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
            pageStatService.incrementView(id);
            return Result.success(article);
        }
    }

    @PostMapping("/add")
    public Result add(@RequestParam String title,@RequestParam String content,@RequestParam MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return Result.error("Image is empty");
        }
        Article article = new Article();
        article.title = title;
        article.content = content;
        Map<String,?> claims = ThreadLocalUtil.get();
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+suffix;
        article.imageUrl = "http://localhost:3001/cover/"+newFileName;
        article.username = claims.get("username").toString();
        article.timestamp = String.valueOf(System.currentTimeMillis());
        file.transferTo(new java.io.File("C:/Users/16939/Desktop/Nouveau dossier/blog/server/cover/"+newFileName));
        articleService.add(article);
        return Result.success();
    }
    @GetMapping("/my")
    public Result my() {
        Map<String,?> claims = ThreadLocalUtil.get();
        return Result.success(articleService.getArticleByUser(claims.get("username").toString()));
    }
    @PostMapping("/update")
    public Result update(@RequestParam String title,@RequestParam String content,@RequestParam MultipartFile file,@RequestParam int id) throws IOException {
        System.out.println(file==null);
        System.out.println(title==null);
        Map<String,?> claims = ThreadLocalUtil.get();
        Article article = articleService.getArticle(id);
        article.content = content;
        article.title = title;
        article.timestamp = String.valueOf(System.currentTimeMillis());
        if(claims.get("username").equals(article.username)){
            if(!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
                String newFileName = UUID.randomUUID().toString() + suffix;
                file.transferTo(new java.io.File("C:/Users/16939/Desktop/Nouveau dossier/blog/server/cover/" + newFileName));
                article.imageUrl = "http://localhost:3001/cover/" + newFileName;
                System.out.println(article.imageUrl);
            }
            articleService.update(article);
            return Result.success();
        }else{
            return Result.error("Unauthorized");
        }
    }

}
