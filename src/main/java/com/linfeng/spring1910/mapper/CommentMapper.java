package com.linfeng.spring1910.mapper;

import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comments WHERE article_id=#{id}")
    public List<Comment> get(int id);

    @Insert("INSERT INTO comments (author,date,likes,dislikes,article_id,text) VALUES (#{author},now(),#{likes},#{dislikes},#{article_id},#{text})")
    public void set(Comment comment);
}
