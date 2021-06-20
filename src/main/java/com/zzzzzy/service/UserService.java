package com.zzzzzy.service;

import com.zzzzzy.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User queryByName(String username);

    public List<Map<String, Object>> getUserPower(String username);
}
