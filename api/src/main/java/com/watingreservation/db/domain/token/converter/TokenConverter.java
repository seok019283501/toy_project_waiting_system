package com.watingreservation.db.domain.token.converter;


import com.watingreservation.db.common.annotation.Converter;
import com.watingreservation.db.common.error.ErrorCode;
import com.watingreservation.db.common.exception.ApiException;
import com.watingreservation.db.domain.token.controller.model.TokenResponse;
import com.watingreservation.db.domain.token.model.TokenDto;
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
