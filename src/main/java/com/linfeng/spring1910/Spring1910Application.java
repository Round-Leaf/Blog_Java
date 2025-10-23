package com.linfeng.spring1910;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.imported.beans;

@SpringBootApplication
public class Spring1910Application{
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring1910Application.class, args);
    }
}
