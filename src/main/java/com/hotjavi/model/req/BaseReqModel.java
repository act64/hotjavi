package com.hotjavi.model.req;

import java.util.HashMap;

/**
 * Created by ylei on 17-4-6.
 */
public class BaseReqModel<T> {
    private RequestHead header;
    private T body;

    public RequestHead getHeader() {
        return header;
    }

    public void setHeader(RequestHead header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
