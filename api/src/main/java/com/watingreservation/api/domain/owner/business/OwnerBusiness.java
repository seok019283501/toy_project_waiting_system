package com.watingreservation.api.domain.user.business;

import com.watingreservation.api.common.annotation.Business;
import com.watingreservation.api.domain.token.business.TokenBusiness;
import com.watingreservation.api.domain.token.controller.model.TokenResponse;
import com.watingreservation.api.domain.user.controller.model.UserLoginRequest;
import com.watingreservation.api.domain.user.controller.model.UserRegisterRequest;
import com.watingreservation.api.domain.user.controller.model.UserResponse;
import com.watingreservation.api.domain.user.converter.UserConverter;
import com.watingreservation.api.domain.user.service.UserService;
import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    //유저 회원 가입
    public UserResponse register(UserRegisterRequest request,UserStatus userStatus,UserAccess userAccess){
        var entity = userConverter.toEntity(request);
        System.out.println(userStatus);
        var newEntity = userService.register(entity, userStatus,userAccess);
        var res = userConverter.toResponse(newEntity);
        return res;
    }

    //로그인
    public TokenResponse login(UserLoginRequest userLoginRequest, UserStatus userStatus, UserAccess userAccess){
        var user = userService.login(userLoginRequest,userStatus,userAccess);
        var token = tokenBusiness.issueToken(user);
        return token;
    }
    public UserResponse me(String id){
        var user = userService.getUserWithThrow(id);
        var res = userConverter.toResponse(user);
        return res;
    }
    public List<UserResponse> list(UserAccess userAccess,String managerId){
        var userList = userService.getListWithThrow(userAccess,managerId);
        var res =userList.stream()
                        .map(it->{
                            return userConverter.toResponse(it);
                        }).collect(Collectors.toList());

        return res;
    }

}