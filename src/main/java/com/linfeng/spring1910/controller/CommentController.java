package com.linfeng.spring1910.controller;

import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.server.CommentService;
import com.linfeng.spring1910.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/get")
    public Result<?> get(@RequestParam int id){
        List<Comment> comments = commentService.get(id);
        if(comments.isEmpty()){
            return Result.error("Article doesn't exist");
        }else{
            return Result.success(comments);
        }
    }
    @PostMapping("/set")
    public Result<?> set(@Validated @RequestBody Comment comment){
        Map<String,?> claims = ThreadLocalUtil.get();
        if(claims.get("username")==null){
            return Result.error("Unauthorized");
        }
        comment.likes = 0;
        comment.dislikes = 0;
        comment.author = claims.get("username").toString();
        System.out.println(comment.text);
        return Result.success(commentService.set(comment));
    }
}
