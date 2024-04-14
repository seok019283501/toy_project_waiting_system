package com.watingreservation.db.domain.token.service;

import com.watingreservation.db.common.error.ErrorCode;
import com.watingreservation.db.common.exception.ApiException;
import com.watingreservation.db.domain.token.helper.TokenHelperIfs;
import com.watingreservation.db.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(String userId){
        var data = new HashMap<String,Object>();
        data.put("userId",userId);
        return tokenHelperIfs.issueAccessToken(data);
    }
    public TokenDto issueRefreshToken(String userId){
        var data = new HashMap<String,Object>();
        data.put("userId",userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }
    public String validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var userId = map.get("userId");
        Objects.requireNonNull(userId,()->{throw new ApiException(ErrorCode.NULL_POINT);
        });
        return userId.toString();
    }
}
