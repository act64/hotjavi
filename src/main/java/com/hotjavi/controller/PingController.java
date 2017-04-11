package com.hotjavi.controller;

import com.hotjavi.Util.ResponseUtil;
import com.hotjavi.model.req.BaseReqModel;
import com.hotjavi.model.response.BaseResponse;
import com.hotjavi.model.sql.TokenData;
import com.hotjavi.service.PingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by ylei on 17-4-8.
 */
@Controller
@RequestMapping("/ping")
public class PingController {
    @Resource
    PingService pingService;
    @Resource
    UserUtil userUtil;

    @RequestMapping(value = "/getEmail", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<String>> queryEmail(@RequestBody BaseReqModel reqModel) {
        TokenData tk = userUtil.queryToken(reqModel);
        if (tk == null) {
            return ResponseUtil.tookenError(String.class);
        }
        String email = pingService.queryEmail(tk);
        return ResponseUtil.getresPonse(String.class, email, HttpStatus.OK);
    }


}
