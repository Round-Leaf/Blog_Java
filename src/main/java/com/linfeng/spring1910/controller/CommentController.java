package com.linfeng.spring1910.controller;

import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.CommentVote;
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
    public Result<?> get(@RequestParam(value="id") Integer articleId){
        List<Comment> comments = commentService.get(articleId);
        if(comments.isEmpty()){
            return Result.error("Article doesn't exist");
        }else{
            return Result.success(comments);
        }
    }

    @PostMapping("/set")
    public Result<?> set(@Validated @RequestBody Comment comment){
        Map<String,String> claims = ThreadLocalUtil.get();
        if(claims.get("username")==null){
            return Result.error("Unauthorized");
        }
        comment.likes = 0;
        comment.dislikes = 0;
        comment.author_id = Integer.parseInt(claims.get("id"));
        System.out.println(comment.text);
        return Result.success(commentService.set(comment));
    }

//    public Result getVote(@RequestParam Integer articleId){
//        //commentService.
//    }


    @PostMapping("/vote")
    public Result vote(@Validated @RequestBody CommentVote commentVote){
        Map<String,?> claims = ThreadLocalUtil.get();
        if(claims.get("username")==null){
            return Result.error("Unauthorized");
        }
        commentVote.userId = Integer.parseInt(claims.get("id").toString());
        commentService.vote(commentVote);
        return Result.success();
    }
}
