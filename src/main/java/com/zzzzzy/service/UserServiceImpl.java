package com.zzzzzy.service;

import com.zzzzzy.dao.UserMapper;
import com.zzzzzy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }

    @Override
    public List<Map<String, Object>> getUserPower(String username) {
        return userMapper.getUserPower(username);
    }


}
