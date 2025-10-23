package com.linfeng.spring1910.mapper;

import com.linfeng.spring1910.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * from users where username=#{username}")
    public User findByUserName(String username);

    @Insert("INSERT INTO users(username,password,timestamp) VALUES(#{username},#{password},#{timestamp})")
    public void register(String username, String password,String timestamp);
}
