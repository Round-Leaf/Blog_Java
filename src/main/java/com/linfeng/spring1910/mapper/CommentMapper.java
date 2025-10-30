package com.linfeng.spring1910.mapper;

import com.linfeng.spring1910.pojo.Comment;
import com.linfeng.spring1910.pojo.CommentVote;
import com.linfeng.spring1910.pojo.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT *,(SELECT username FROM users WHERE id=author_id) AS author " +
            "FROM comments WHERE article_id=#{articleId}")
    public List<Comment> get(Integer articleId);

    @Insert("INSERT INTO comments (author_id,date,likes,dislikes,article_id,text) " +
            "VALUES (#{authorId},now(),#{likes},#{dislikes},#{article_id},#{text})")
    public void set(Comment comment);

    @Select("SELECT comment_id,vote_type,user_id," +
            "COUNT(CASE vote_type WHEN 1 THEN 1 END) AS likes," +
            "COUNT(CASE vote_type WHEN -1 THEN 1 END) AS dislikes " +
            "from comment_votes " +
            "INNER JOIN comments where article_id=#{articleId}")
    public List<CommentVote> getVotes(Integer articleId);

    @Insert("INSERT INTO comment_votes (comment_id,user_id,vote_type) VALUES (#{commentId},#{userId},#{voteType})" +
            "ON DUPLICATE KEY UPDATE vote_type=#{voteType}")
    public void vote(CommentVote commentVote);
}
