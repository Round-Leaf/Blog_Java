package com.linfeng.spring1910.controller;

import com.linfeng.spring1910.utils.JwtUtil;
import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.pojo.User;
import com.linfeng.spring1910.server.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true",
        allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{3,20}$") String username, @Pattern(regexp = "^\\S{3,20}$") String password){
        if(username==null || password==null)    return Result.error("Parameter Error");
        User u = userService.findByUserName(username);
        System.out.println("here");
        if(u==null){
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("User Already Exists");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,20}$") String username, @Pattern(regexp = "^\\S{3,20}$") String password){
        User loginUser = userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("User doesn't exist");
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(password,loginUser.getPassword())){
                Map<String,String> claims = new HashMap<>();
                claims.put("username", loginUser.getUsername());
                return Result.success(JwtUtil.genToken(claims));
            }else{
                return Result.error("Wrong Password");
            }
        }
    }


}
