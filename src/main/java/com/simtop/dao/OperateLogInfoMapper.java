package com.simtop.dao;

import com.simtop.entity.OperateLogInfo;

public interface OperateLogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperateLogInfo record);

    int insertSelective(OperateLogInfo record);

    OperateLogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperateLogInfo record);

    int updateByPrimaryKey(OperateLogInfo record);
}