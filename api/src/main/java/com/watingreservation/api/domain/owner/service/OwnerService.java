package com.watingreservation.api.domain.user.service;

import com.watingreservation.api.common.error.ErrorCode;
import com.watingreservation.api.common.error.UserError;
import com.watingreservation.api.common.exception.ApiException;
import com.watingreservation.api.domain.user.controller.model.UserLoginRequest;
import com.watingreservation.db.user.UserEntity;
import com.watingreservation.db.user.UserRepository;
import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //사용자 회원가입
    public UserEntity register(UserEntity userEntity,UserStatus userStatus,UserAccess userAccess){
        return Optional.ofNullable(userEntity)
                .map(it->{
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    userEntity.setStatus(userStatus);
                    userEntity.setAccess(userAccess);
                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    return userRepository.save(userEntity);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public UserEntity login(UserLoginRequest userLoginRequest, UserStatus userStatus, UserAccess userAccess){
        var user = getUserWithThrow(userLoginRequest,userStatus,userAccess);
        return user;
    }

    public UserEntity getUserWithThrow(UserLoginRequest userLoginRequest,UserStatus userStatus,UserAccess userAccess){
        var user = userRepository.findFirstByIdAndStatusAndAccessOrderByIdDesc(
                userLoginRequest.getId(),
                userStatus,
                userAccess
        ).orElseThrow(()->
                new ApiException(ErrorCode.NULL_POINT));
        if(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())){
            return user;
        }
        throw new ApiException(ErrorCode.NULL_POINT);
    }
    public UserEntity getUserWithThrow(String userId){
        return userRepository.findFirstByIdOrderByIdDesc(
                userId
        ).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserEntity> getListWithThrow(UserAccess userAccess,String managerId){
        var manager = getUserWithThrow(managerId);
        if(manager.getStatus().equals(UserStatus.APPROVE)){
            return userRepository.findFirstByAccessOrderByRegisteredAtAsc(userAccess);
        }
        throw new ApiException(UserError.AUTHORIZATION_ACCESS_NOT_FOUNT,"미승인");
    }
}
