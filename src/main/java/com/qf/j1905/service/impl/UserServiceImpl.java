package com.qf.j1905.service.impl;

import com.qf.j1905.dao.UserMapper;
import com.qf.j1905.domain.User;
import com.qf.j1905.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer saveUser(User user) {
        return userMapper.addUser(user);
    }
}
