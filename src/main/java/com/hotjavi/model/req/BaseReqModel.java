package com.hotjavi.model.req;

import java.util.HashMap;

/**
 * Created by ylei on 17-4-6.
 */
public class BaseReqModel<T> {
    private HashMap<String,Object> header;
    private T body;

    public HashMap<String, Object> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, Object> header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
