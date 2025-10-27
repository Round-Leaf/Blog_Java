package com.linfeng.spring1910.server;

import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.Result;

import java.util.List;


public interface CommentService {
    public List<Comment> get(int id);
    public Result<String> set(Comment comment);
}
