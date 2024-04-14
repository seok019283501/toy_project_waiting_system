package com.watingreservation.db.domain.token.business;

import com.watingreservation.db.common.annotation.Business;
import com.watingreservation.db.common.error.ErrorCode;
import com.watingreservation.db.common.exception.ApiException;
import com.watingreservation.db.domain.token.controller.model.TokenResponse;
import com.watingreservation.db.domain.token.converter.TokenConverter;
import com.watingreservation.db.domain.token.service.TokenService;
import com.watingreservation.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(UserEntity user){
        return Optional.ofNullable(user)
                .map(ue->{
                    return ue.getId();
                })
                .map(userId->{
                    var accessToken = tokenService.issueAccessToken(userId);
                    var refreshToken = tokenService.issueRefreshToken(userId);
                    return tokenConverter.toResponse(accessToken,refreshToken);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public String validationAccessToken(String tokenAccess){
        var userId = tokenService.validationToken(tokenAccess);
        return userId;
    }
}
