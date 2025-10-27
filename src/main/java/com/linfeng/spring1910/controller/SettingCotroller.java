package com.linfeng.spring1910.controller;

import com.linfeng.spring1910.pojo.Result;
import com.linfeng.spring1910.pojo.Setting;
import com.linfeng.spring1910.server.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingCotroller {
    @Autowired
    SettingService settingService;
    @GetMapping("/get")
    public Result<Setting> get(){
        return Result.success(settingService.get());
    }
}
