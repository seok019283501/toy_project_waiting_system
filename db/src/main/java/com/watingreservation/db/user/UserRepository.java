package com.watingreservation.db.user;

import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findFirstByAccessOrderByIdDesc(UserAccess access);
    Optional<UserEntity> findFirstByIdOrderByIdDesc(String id);
    Optional<UserEntity> findFirstByIdAndPasswordAndStatusAndAccessOrderByIdDesc(String userId, String password, UserStatus userStatus, UserAccess userAccess);
}
