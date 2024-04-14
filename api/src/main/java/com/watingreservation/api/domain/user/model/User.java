package com.watingreservation.api.domain.user.model;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public String id;
    public String password;
    public String name;
    public String phone_number;
    public UserStatus status;
    public UserAccess access;
    public LocalDateTime registered_at;
    public LocalDateTime unregistered_at;
}
