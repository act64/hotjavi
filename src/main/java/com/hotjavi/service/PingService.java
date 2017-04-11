package com.hotjavi.service;

import com.hotjavi.mapper.PingMapper;
import com.hotjavi.model.sql.TokenData;
import com.hotjavi.serviceinterface.IPIngService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ylei on 17-4-8.
 */
@Service("pingService")
public class PingService implements IPIngService {
    @Resource
    PingMapper pingMapper;
    public TokenData checkToken(TokenData tokenData) {
        return pingMapper.checkToken(tokenData);
    }

    public String queryEmail(TokenData tokenData) {
        return pingMapper.queryEmail(tokenData);
    }
}
