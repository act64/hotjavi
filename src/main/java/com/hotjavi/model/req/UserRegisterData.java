package com.hotjavi.model.req;

/**
 * Created by ylei on 17-4-4.
 */
public class UserRegisterData extends UserAdminData {
    private String email;
    private String nickname;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
