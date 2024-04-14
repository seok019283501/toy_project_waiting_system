package com.watingreservation.api.domain.token.converter;


import com.watingreservation.api.common.annotation.Converter;
import com.watingreservation.api.common.error.ErrorCode;
import com.watingreservation.api.common.exception.ApiException;
import com.watingreservation.api.domain.token.controller.model.TokenResponse;
import com.watingreservation.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken){
        Objects.requireNonNull(accessToken,()->{throw new ApiException(ErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken,()->{throw new ApiException(ErrorCode.NULL_POINT);
        });
        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpriedAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpriedAt())
                .build();
    }

}
