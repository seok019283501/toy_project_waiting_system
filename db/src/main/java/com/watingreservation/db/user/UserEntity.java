package com.watingreservation.db.user;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity{
    @Id
    @Column(length = 50)
    public String id;
    @Column(length = 50)
    public String password;
    @Column(length = 50)
    public String name;
    @Column(length = 20)
    public String phone_number;
    @Column(length = 50)
    public UserStatus status;
    @Column(length = 50)
    public UserAccess access;
    public LocalDateTime registered_at;
    public LocalDateTime unregistered_at;
}
