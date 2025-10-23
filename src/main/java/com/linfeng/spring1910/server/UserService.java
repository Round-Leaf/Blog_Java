package com.linfeng.spring1910.server;

import com.linfeng.spring1910.pojo.Articles;
import com.linfeng.spring1910.pojo.User;


public interface UserService {

    User findByUserName(String username);

    void register(String username, String password);
}
