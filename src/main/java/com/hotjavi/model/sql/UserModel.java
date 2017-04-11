package com.hotjavi.model.sql;

import com.hotjavi.model.req.UserRegisterData;

/**
 * Created by ylei on 17-4-8.
 */
public class UserModel extends UserRegisterData {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
