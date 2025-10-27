package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.mapper.CommentMapper;
import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.server.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> get(int id) {
       return commentMapper.get(id);
    }

    @Override
    public Result<String> set(Comment comment) {
        commentMapper.set(comment);
        return Result.success("Comment added");
    }
}
