package com.qf.j1905.dao;

import com.qf.j1905.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<Permission> findPermissionsByUserId(Integer userId);
}
