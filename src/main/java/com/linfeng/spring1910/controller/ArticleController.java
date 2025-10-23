package com.linfeng.spring1910.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.utils.JwtUtil;
import com.linfeng.spring1910.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/my")
    public Result my() {
        Map<String,?> claims = ThreadLocalUtil.get();
        return Result.success("Data of Articles");
    }
    public Result
}
