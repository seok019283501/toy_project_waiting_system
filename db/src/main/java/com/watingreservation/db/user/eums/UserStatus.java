package com.watingreservation.db.user.eums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    REGISTERED("등록"),
    UNREGISTERED("해제"),
    NOT_APPROVE("미승인"),
    APPROVE("승인")
    ;

    private final String description;
}
