package com.hotjavi.controller;

import com.hotjavi.Util.*;
import com.hotjavi.model.req.UserAdminData;
import com.hotjavi.model.req.UserRegisterData;
import com.hotjavi.model.req.BaseReqModel;
import com.hotjavi.model.response.BaseResponse;
import com.hotjavi.model.response.UserTokenData;
import com.hotjavi.model.sql.TokenData;
import com.hotjavi.model.sql.UserModel;
import com.hotjavi.serviceinterface.IUserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ylei on 17-4-4.
 */
@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<Object>> insertUser(@RequestBody BaseReqModel<UserRegisterData> userRegisterData, @RequestHeader HashMap<String, String> header) throws Exception {
//        UserRegisterData userRegisterData=new UserRegisterData();
//        userRegisterData.setAccount(request.getParameter("account"));
//        userRegisterData.setPwd(request.getParameter("pwd"));
//        userRegisterData.setEmail(request.getParameter("email"));
//        userRegisterData.setNickname(request.getParameter("nickname"));

        if (!CheckInvailedUtil.checkAccount(userRegisterData.getBody().getAccount())) {
            return ResponseUtil.getresPonse(Object.class,null, HttpStatus.BAD_REQUEST, "帐号需要以英文字母开头，长度为5-16位");
        }
        String userpwd = getDecodeStr(userRegisterData.getBody().getPwd(), header);
        if (!CheckInvailedUtil.checkpwd(userpwd)) {
            return ResponseUtil.getresPonse(Object.class,null, HttpStatus.BAD_REQUEST, "密码需要以英文字母开头，长度为5-16位");

        }
        if (!CheckInvailedUtil.checkEmail(userRegisterData.getBody().getEmail())) {
            return ResponseUtil.getresPonse(Object.class,null, HttpStatus.BAD_REQUEST, "email格式有误");

        }
        if (!CheckInvailedUtil.checkNickName(userRegisterData.getBody().getNickname())) {
            return ResponseUtil.getresPonse(Object.class,null, HttpStatus.BAD_REQUEST, "昵称格式有误");
        }
        try {
            userRegisterData.getBody().setPwd(Md5Util.EncoderByMd5(userpwd));
            userService.insertUser(userRegisterData.getBody());
            return ResponseUtil.getresPonse(Object.class,null, HttpStatus.OK, "ok");
        } catch (DuplicateKeyException e) {
            String message = e.getCause().getMessage();
            if (message.contains("account")) {
                return ResponseUtil.getresPonse(Object.class,null, HttpStatus.EXPECTATION_FAILED, "当前用户名已存在");
            } else {
                return ResponseUtil.getresPonse(Object.class,null, HttpStatus.EXPECTATION_FAILED, "当前邮箱已使用");
            }
        }
    }

    private String getDecodeStr(String pwd,  HashMap<String, String> header) {
        return new String(AESUtils.decrypt(AESUtils.parseHexStr2Byte(pwd), header.get("time-span") + "-1000"));
    }
    private String getNewToken() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return Md5Util.md5(UUID.randomUUID().toString()).substring(8,24);
    }
@RequestMapping(value = "/admin" ,method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<TokenData>> UserAdmin(@RequestBody BaseReqModel<UserAdminData> userAdminDataBaseReqModel, @RequestHeader HashMap<String, String> header) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserAdminData useadminData = userAdminDataBaseReqModel.getBody();
        if (!CheckInvailedUtil.checkAccount(useadminData.getAccount())) {
            return ResponseUtil.getresPonse(TokenData.class,null, HttpStatus.BAD_REQUEST, "帐号需要以英文字母开头，长度为5-16位");
        }
        String userpwd = getDecodeStr(useadminData.getPwd(), header);
        if (!CheckInvailedUtil.checkpwd(userpwd)) {
            return ResponseUtil.getresPonse(TokenData.class,null, HttpStatus.BAD_REQUEST, "密码需要以英文字母开头，长度为5-16位");

        }
        useadminData.setPwd(Md5Util.EncoderByMd5(userpwd));
        UserModel userModel = userService.checkAdmin(useadminData);
        if (userModel==null){
            return ResponseUtil.getresPonse(TokenData.class,null, HttpStatus.BAD_REQUEST, "帐号验证失败");

        }
        TokenData tk=new TokenData();
        tk.setToken(getNewToken());
        tk.setUid(userModel.getId());
        userService.insertToken(tk);
        return ResponseUtil.getresPonse(TokenData.class,tk,HttpStatus.OK);
    }


}
