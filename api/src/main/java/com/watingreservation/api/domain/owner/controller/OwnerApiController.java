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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserBusiness userBusiness;



    @GetMapping("/list")
    public Api<List<UserResponse>> list(
            @RequestParam
            UserAccess access,
            @RequestParam
            String managerId
    ){
        var list = userBusiness.list(access,managerId);
        return Api.OK(list);
    }
}
