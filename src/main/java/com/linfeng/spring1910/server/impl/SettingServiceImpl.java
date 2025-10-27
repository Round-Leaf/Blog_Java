package com.linfeng.spring1910.server.impl;

import com.linfeng.spring1910.mapper.SettingMapper;
import com.linfeng.spring1910.pojo.Setting;
import com.linfeng.spring1910.server.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingMapper settingMapper;
    @Override
    public Setting get() {
       return settingMapper.get();
    }
}
