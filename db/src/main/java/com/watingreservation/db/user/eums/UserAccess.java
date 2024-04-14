package com.watingreservation.db.user.eums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserAccess {
    USER("USER"),
    OWNER("OWNER"),
    MANGER("MANAGER")
    ;

    private final String description;
}
