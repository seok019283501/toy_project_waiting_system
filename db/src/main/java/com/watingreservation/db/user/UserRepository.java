package com.watingreservation.db.user;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findFirstByIdAndStatusAndAccessOrderByIdDesc(String userId, UserStatus status, UserAccess access);
}
