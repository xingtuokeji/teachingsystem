package com.simtop.service.impl;

import com.simtop.dao.UserMapper;
import com.simtop.entity.User;
import com.simtop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User vlogin(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
