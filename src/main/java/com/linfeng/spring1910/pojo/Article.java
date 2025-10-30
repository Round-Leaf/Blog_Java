package com.linfeng.spring1910.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class Article {
    public int id;
    @NotEmpty
    public String title;
    @NotEmpty
    public String content;
    public String imageUrl;
    public String timestamp;
    public int views;
    public String username;
}
