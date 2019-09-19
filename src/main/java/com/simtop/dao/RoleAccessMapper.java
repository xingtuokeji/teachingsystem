package com.simtop.dao;

import com.simtop.entity.RoleAccess;

public interface RoleAccessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAccess record);

    int insertSelective(RoleAccess record);

    RoleAccess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAccess record);

    int updateByPrimaryKey(RoleAccess record);
}