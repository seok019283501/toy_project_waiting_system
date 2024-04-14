package com.watingreservation.api.domain.user.controller;

import com.watingreservation.api.common.api.Api;
import com.watingreservation.api.domain.user.business.UserBusiness;
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
    @PostMapping("/user-register")
    public Api<UserResponse> userRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.REGISTERED, UserAccess.USER);
        return Api.OK(user);
    }
    @PostMapping("/owener-register")
    public Api<UserResponse> owenerRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.NOT_APPROVE, UserAccess.OWNER);
        return Api.OK(user);
    }
    @PostMapping("/manager-register")
    public Api<UserResponse> managerRegister(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest
    ){
        var user = userBusiness.register(userRegisterRequest, UserStatus.NOT_APPROVE, UserAccess.MANGER);
        return Api.OK(user);
    }

}
