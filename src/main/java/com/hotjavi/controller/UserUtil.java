package com.hotjavi.controller;

import com.hotjavi.mapper.PingMapper;
import com.hotjavi.model.req.BaseReqModel;
import com.hotjavi.model.sql.TokenData;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ylei on 17-4-8.
 */
@Service("userUtil")
public class UserUtil {
    @Resource
    PingMapper pingMapper;
    public  TokenData queryToken(BaseReqModel model){
        try {
            if (model.getHeader().getToken()!=null&&model.getHeader().getUid()!=null){
                TokenData tokenData=new TokenData();
                tokenData.setUid( model.getHeader().getUid());
                tokenData.setToken((String) model.getHeader().getToken());
               return pingMapper.checkToken(tokenData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
