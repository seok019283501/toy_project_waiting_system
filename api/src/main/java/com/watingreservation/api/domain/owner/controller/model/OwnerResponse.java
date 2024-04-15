package com.watingreservation.api.domain.user.controller.model;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    public String id;
    public String name;
    public String phoneNumber;
    public UserStatus status;
    public UserAccess access;
    public LocalDateTime registeredAt;
    public LocalDateTime unregisteredAt;
}
