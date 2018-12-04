package com.seawater.mybatisPlus.service.impl;

import com.seawater.mybatisPlus.mapper.UserInfo2Mapper;
import com.seawater.mybatisPlus.service.db2.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhouhaishui on 2018/12/4.
 */
@Service
public class UserInfo2ServiceImpl implements IUserInfoService{

    @Autowired
    UserInfo2Mapper userInfo2Mapper;

    @Override
    public List<Map> queryUserInfo(Map map) {
        return userInfo2Mapper.queryUserInfo(map);
    }
}
