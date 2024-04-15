package com.watingreservation.api.domain.store.controller;

import com.watingreservation.api.common.api.Api;
import com.watingreservation.api.domain.store.business.StoreBusiness;
import com.watingreservation.api.domain.store.controller.model.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
public class StoreOpenApiContorller {
    private final StoreBusiness storeBusiness;
    @GetMapping("/")
    public Api<StoreResponse> store(
            @RequestParam
            Long id
    ){
        var res = storeBusiness.store(id);
        return Api.OK(res);
    }
    @GetMapping("/list")
    public Api<List<StoreResponse>> storeList(){
        var list = storeBusiness.storeList();
        return Api.OK(list);
    }
}
