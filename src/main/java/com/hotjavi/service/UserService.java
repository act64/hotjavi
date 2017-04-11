package com.hotjavi.service;

import com.hotjavi.mapper.UserMapper;
import com.hotjavi.model.req.UserAdminData;
import com.hotjavi.model.req.UserRegisterData;
import com.hotjavi.model.sql.TokenData;
import com.hotjavi.model.sql.UserModel;
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

    public UserModel checkAdmin(UserAdminData adminData) {
     return    userMapper.checkAdmin(adminData);
    }

    public void insertToken(TokenData tokenData) {
        userMapper.insertToken(tokenData);
    }
}
