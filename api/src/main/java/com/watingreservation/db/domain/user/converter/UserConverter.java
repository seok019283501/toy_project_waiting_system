package com.watingreservation.db.domain.user.converter;

import com.watingreservation.db.common.annotation.Converter;
import com.watingreservation.db.common.error.ErrorCode;
import com.watingreservation.db.common.exception.ApiException;
import com.watingreservation.db.domain.user.controller.model.UserRegisterRequest;
import com.watingreservation.db.domain.user.controller.model.UserResponse;
import com.watingreservation.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest userRegisterRequest){
        return Optional.ofNullable(userRegisterRequest)
                .map(it->{
                    return UserEntity.builder()
                            .id(userRegisterRequest.getId())
                            .password(userRegisterRequest.getPassword())
                            .name(userRegisterRequest.getName())
                            .phone_number(userRegisterRequest.getPhone_number())
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"userRegisterRequest null"));
    }
    public UserResponse toResponse(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it->{
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .phone_number(userEntity.phone_number)
                            .access(userEntity.getAccess())
                            .status(userEntity.getStatus())
                            .registered_at(userEntity.registered_at)
                            .unregistered_at(userEntity.unregistered_at)
                            .build();
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"user null"));
    }
}
