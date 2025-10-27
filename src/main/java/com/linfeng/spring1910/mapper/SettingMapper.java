package com.linfeng.spring1910.mapper;

import com.linfeng.spring1910.pojo.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettingMapper {
    @Select("SELECT * FROM settings")
    public Setting get();
}
