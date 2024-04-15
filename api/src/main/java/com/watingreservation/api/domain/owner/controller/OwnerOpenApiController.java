package com.watingreservation.api.domain.user.controller;

import com.watingreservation.api.common.api.Api;
import com.watingreservation.api.domain.token.controller.model.TokenResponse;
import com.watingreservation.api.domain.user.business.UserBusiness;
import com.watingreservation.api.domain.user.controller.model.UserLoginRequest;
import com.watingreservation.api.domain.user.controller.model.UserRegisterRequest;
import com.watingreservation.api.domain.user.controller.model.UserResponse;
import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {
    private final UserBusiness userBusiness;
    @PostMapping("/user_register")
    public Api<UserResponse> userRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.REGISTERED, UserAccess.USER);
        return Api.OK(user);
    }
    @PostMapping("/owner_register")
    public Api<UserResponse> owenerRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.NOT_APPROVE, UserAccess.OWNER);
        return Api.OK(user);
    }
    @PostMapping("/manager_register")
    public Api<UserResponse> managerRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.NOT_APPROVE, UserAccess.MANAGER);
        return Api.OK(user);
    }

    //user login
    @PostMapping("/user_login")
    public Api<TokenResponse> userLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.REGISTERED, UserAccess.ROLE_USER);
        return Api.OK(user);
    }
    //점주 로그인
    @PostMapping("/owner_login")
    public Api<TokenResponse> ownerLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.APPROVE, UserAccess.ROLE_OWNER);
        return Api.OK(user);
    }
    //관리자 로그인
    @PostMapping("/manager_login")
    public Api<TokenResponse> managerLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.APPROVE, UserAccess.ROLE_MANAGER);
        return Api.OK(user);
    }

}
