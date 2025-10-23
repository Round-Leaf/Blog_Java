package com.linfeng.spring1910.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    @Value("${v}")
    public String v;

    @RequestMapping("/hello")
    public String hello(){
        return "hello"+v;
    }
}
