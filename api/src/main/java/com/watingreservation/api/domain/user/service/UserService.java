package com.watingreservation.api.domain.user.service;

import com.watingreservation.api.common.error.ErrorCode;
import com.watingreservation.api.common.exception.ApiException;
import com.watingreservation.db.user.UserEntity;
import com.watingreservation.db.user.UserRepository;
import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //사용자 회원가입
    public UserEntity register(UserEntity userEntity,UserStatus userStatus,UserAccess userAccess){
        return Optional.ofNullable(userEntity)
                .map(it->{
                    userEntity.setRegistered_at(LocalDateTime.now());
                    userEntity.setStatus(userStatus);
                    userEntity.setAccess(userAccess);
                    return userRepository.save(userEntity);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public UserEntity login(String userId,String password,UserStatus userStatus,UserAccess userAccess){
        var user = getUserWithThrow(userId,password,userStatus,userAccess);
        return user;
    }

    public UserEntity getUserWithThrow(String userId,String password,UserStatus userStatus,UserAccess userAccess){
        return userRepository.findFirstByIdAndPasswordAndStatusAndAccessOrderByIdDesc(
                userId,
                password,
                userStatus,
                userAccess
        ).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
    public UserEntity getUserWithThrow(String userId){
        return userRepository.findFirstByIdOrderByIdDesc(
                userId
        ).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
