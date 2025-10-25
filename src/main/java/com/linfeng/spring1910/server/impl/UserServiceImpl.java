package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.mapper.UserMapper;
import com.linfeng.spring1910.pojo.User;
import com.linfeng.spring1910.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //System.out.println(username);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        userMapper.register(username,hashedPassword,new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }


}
