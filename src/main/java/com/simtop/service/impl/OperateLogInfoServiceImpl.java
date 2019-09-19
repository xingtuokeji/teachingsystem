package com.simtop.service.impl;

import com.simtop.dao.OperateLogInfoMapper;
import com.simtop.entity.OperateLogInfo;
import com.simtop.service.OperateLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperateLogInfoServiceImpl implements OperateLogInfoService {

    @Autowired
    private OperateLogInfoMapper operateLogInfoMapper;

    @Override
    public int insert(OperateLogInfo operateLogInfo) {
        return operateLogInfoMapper.insert(operateLogInfo);
    }

    @Override
    public int update(OperateLogInfo operateLogInfo) {
        return operateLogInfoMapper.updateByPrimaryKeySelective(operateLogInfo);
    }
}
