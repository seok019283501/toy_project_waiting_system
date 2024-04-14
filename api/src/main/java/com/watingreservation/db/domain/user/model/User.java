package com.watingreservation.db.domain.user.model;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
