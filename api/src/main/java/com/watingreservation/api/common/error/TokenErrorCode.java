package com.watingreservation.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{
    INVALID_TOKEN(400,2001,"유효하지 않은 토큰"),
    EXPIRED_TOKEN(400,2002,"만료된 토큰"),
    TOKEN_EXCEPTUON(400,2003,"토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUNT(400,2004,"인증 토큰 없음")
        ;
    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
