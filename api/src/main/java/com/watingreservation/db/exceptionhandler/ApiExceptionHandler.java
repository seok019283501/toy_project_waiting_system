package com.watingreservation.db.exceptionhandler;

import com.watingreservation.db.common.api.Api;
import com.watingreservation.db.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//api 예외 발생시 전역 관리
@Slf4j
//json 형태로 변환해서 반환
@RestController
@Order(value = Integer.MIN_VALUE)// 최우서
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiResponseEntity(ApiException apiException){
        log.info("", apiException);
        var errorCode = apiException.getErrorCodeIfs();
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(Api.ERROR(errorCode,apiException.getErrorDescription()));
    }


}
