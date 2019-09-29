package com.qf.j1905.dao;

import com.qf.j1905.domain.UserCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserCodeMapper {
    List<UserCode> findByUserMail(String userMail);
    /**
     * 往验证码的表里面存当前邮箱用户的验证码（原表里面此用户无数据）
     */
    void save(UserCode userCode);
    /**
     * 往验证码的表里面存当前邮箱用户的验证码（原表里面此用户有数据）
     */
    void update(UserCode userCode);
}
