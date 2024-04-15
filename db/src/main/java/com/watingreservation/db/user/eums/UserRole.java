package com.watingreservation.db.user.eums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserAccess {
    USER("USER"),
    OWNER("OWNER"),
    MANAGER("MANAGER")
    ;

    private final String description;
}
