package com.hotjavi.Util;

import com.hotjavi.model.response.BaseResponse;
import com.hotjavi.model.response.ResponseHead;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by ylei on 17-4-6.
 */
public class ResponseUtil {

    public static <T> ResponseEntity< BaseResponse<T>>tookenError(Class<T> tClass){
        return getresPonse(tClass,null,HttpStatus.NON_AUTHORITATIVE_INFORMATION,"token 非法");
    }
    public static<T> ResponseEntity< BaseResponse<T>> getresPonse(Class<T> tClass, T responseBody, HttpStatus httpStatus){
        BaseResponse<T> baseResponse = gettBaseResponse(tClass,responseBody, httpStatus);
        ResponseEntity< BaseResponse<T>> responseResponseEntity=new ResponseEntity(baseResponse,HttpStatus.OK);
        return responseResponseEntity;
    }

    public static<T> ResponseEntity< BaseResponse<T>> getresPonse(Class<T> tClass,T responseBody, HttpStatus httpStatus,String message){
        BaseResponse<T> baseResponse = gettBaseResponse(tClass,responseBody, httpStatus);
        baseResponse.getHeader().setMessage(message);
        ResponseEntity< BaseResponse<T>> responseResponseEntity=new ResponseEntity(baseResponse,httpStatus);
        return responseResponseEntity;
    }

    private static <T> BaseResponse<T> gettBaseResponse(Class<T> tClass, T responseBody, HttpStatus httpStatus) {
        BaseResponse<T> baseResponse=new BaseResponse<T>();
        baseResponse.setHeader(new ResponseHead());
        baseResponse.setBody(responseBody);
        if (httpStatus.equals(HttpStatus.OK)){
            baseResponse.getHeader().setStatus(2000);
            baseResponse.getHeader().setMessage("ok");
        }else  if (httpStatus.equals(HttpStatus.NON_AUTHORITATIVE_INFORMATION)){
            baseResponse.getHeader().setStatus(4000);

        }else {
            baseResponse.getHeader().setStatus(3000);
        }
        return baseResponse;
    }



}
