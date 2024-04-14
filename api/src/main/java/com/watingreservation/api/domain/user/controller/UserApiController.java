package com.watingreservation.api.domain.user.controller;

import com.watingreservation.api.common.api.Api;
import com.watingreservation.api.domain.token.controller.model.TokenResponse;
import com.watingreservation.api.domain.user.business.UserBusiness;
import com.watingreservation.api.domain.user.controller.model.UserLoginRequest;
import com.watingreservation.api.domain.user.controller.model.UserResponse;
import com.watingreservation.db.user.eums.UserAccess;
import com.watingreservation.db.user.eums.UserStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserBusiness userBusiness;

    //user login
    @PostMapping("/user-login")
    public Api<TokenResponse> userLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.REGISTERED, UserAccess.USER);
        return Api.OK(user);
    }
    //점주 로그인
    @PostMapping("/owner-login")
    public Api<TokenResponse> ownerLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.REGISTERED, UserAccess.USER);
        return Api.OK(user);
    }
    //관리자 로그인
    @PostMapping("/manager-login")
    public Api<TokenResponse> managerLogin(
            @Valid
            @RequestBody
            UserLoginRequest request
    ){
        var user = userBusiness.login(request, UserStatus.REGISTERED, UserAccess.USER);
        return Api.OK(user);
    }
}
