package com.linfeng.spring1910.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Comment {
    public int id;
    public int author_id;
    public String author;
    public String date;
    public int likes;
    public int dislikes;
    @NotNull
    public int article_id;
    @NotEmpty
    public String text;
}
