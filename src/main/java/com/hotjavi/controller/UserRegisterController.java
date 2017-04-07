package com.hotjavi.controller;

import com.hotjavi.Util.*;
import com.hotjavi.model.UserRegisterData;
import com.hotjavi.model.req.BaseReqModel;
import com.hotjavi.model.response.BaseResponse;
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
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by ylei on 17-4-4.
 */
@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
 public ResponseEntity<BaseResponse<Object>> insertUser(@RequestBody BaseReqModel<UserRegisterData> userRegisterData, @RequestHeader HashMap<String,String> header) throws Exception {
//        UserRegisterData userRegisterData=new UserRegisterData();
//        userRegisterData.setAccount(request.getParameter("account"));
//        userRegisterData.setPwd(request.getParameter("pwd"));
//        userRegisterData.setEmail(request.getParameter("email"));
//        userRegisterData.setNickname(request.getParameter("nickname"));

        if (!CheckInvailedUtil.checkAccount(userRegisterData.getBody().getAccount())){
            return ResponseUtil.getresPonse(new Object(), HttpStatus.BAD_REQUEST,"帐号需要以英文字母开头，长度为5-16位");
        }
        String userpwd =new String( AESUtils.decrypt(AESUtils.parseHexStr2Byte(userRegisterData.getBody().getPwd()),header.get("time-span")+"-1000"));
        if(!CheckInvailedUtil.checkpwd(userpwd)){
            return ResponseUtil.getresPonse(new Object(), HttpStatus.BAD_REQUEST,"密码需要以英文字母开头，长度为5-16位");

        }
        if (!CheckInvailedUtil.checkEmail(userRegisterData.getBody().getEmail())){
            return ResponseUtil.getresPonse(new Object(), HttpStatus.BAD_REQUEST,"email格式有误");

        }
        if (!CheckInvailedUtil.checkNickName(userRegisterData.getBody().getNickname())){
            return ResponseUtil.getresPonse(new Object(),HttpStatus.BAD_REQUEST,"昵称格式有误");
        }
        try {
            userRegisterData.getBody().setPwd(Md5Util.EncoderByMd5(userpwd));
            userService.insertUser(userRegisterData.getBody());
            return ResponseUtil.getresPonse(new Object(), HttpStatus.OK,"ok");
        } catch (DuplicateKeyException e) {
            String message = e.getCause().getMessage();
            if (message.contains("account")) {
                return ResponseUtil.getresPonse(new Object(),HttpStatus.EXPECTATION_FAILED,"当前用户名已存在");
            }else {
                return ResponseUtil.getresPonse(new Object(),HttpStatus.EXPECTATION_FAILED,"当前邮箱已使用");
            }
        }
    }

}
