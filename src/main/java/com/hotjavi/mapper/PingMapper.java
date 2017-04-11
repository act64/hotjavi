package com.hotjavi.mapper;

import com.hotjavi.model.sql.TokenData;

/**
 * Created by ylei on 17-4-8.
 */
public interface PingMapper {
    public TokenData checkToken(TokenData tokenData);
    public String queryEmail(TokenData tokenData);
}
