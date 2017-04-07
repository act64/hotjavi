package com.hotjavi.service;

import com.hotjavi.mapper.UserMapper;
import com.hotjavi.model.UserRegisterData;
import com.hotjavi.serviceinterface.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ylei on 17-4-4.
 */
@Service("userService")
public class UserService implements IUserService{

    @Resource
    UserMapper userMapper;

    public void insertUser(UserRegisterData userRegisterData) {
        userMapper.insertUser(userRegisterData);
    }
}
