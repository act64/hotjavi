package com.hotjavi.model.req;

/**
 * Created by ylei on 17-4-8.
 */
public class RequestHead {
    Long uid;
    String token;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
