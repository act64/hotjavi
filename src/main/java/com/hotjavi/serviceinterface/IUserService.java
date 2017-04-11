package com.hotjavi.serviceinterface;

import com.hotjavi.model.req.UserAdminData;
import com.hotjavi.model.req.UserRegisterData;
import com.hotjavi.model.sql.TokenData;
import com.hotjavi.model.sql.UserModel;

/**
 * Created by ylei on 17-4-4.
 */
public interface IUserService {
    public void insertUser(UserRegisterData userRegisterData);
    public UserModel checkAdmin(UserAdminData adminData);
    public void insertToken(TokenData tokenData);
}
