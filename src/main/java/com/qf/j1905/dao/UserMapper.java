package com.qf.j1905.dao;

import com.qf.j1905.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer addUser(User user);
    User findByUserId(Integer userId);
}
