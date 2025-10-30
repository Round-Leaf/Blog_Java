package com.linfeng.spring1910.pojo;

import jakarta.validation.constraints.NotNull;

public class CommentVote {
    @NotNull
    public int commentId;
    @NotNull
    public int voteType;

    public int userId;
    public int likes;
    public int dislikes;
}
