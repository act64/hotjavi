package com.hotjavi.model.response;

/**
 * Created by ylei on 17-4-6.
 */
public class BaseResponse<T> {
    private ResponseHead header;
    private T body;

    public ResponseHead getHeader() {
        return header;
    }

    public void setHeader(ResponseHead header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
