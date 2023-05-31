package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.constant.http.CustomResultCode;
import com.example.demo.constant.http.BaseApiResponse.BaseApiResponseBuilder;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private BaseApiResponse<String> apiResponse;
    
    public UserNotFoundException(Long userSeqId) {
        
        apiResponse = new BaseApiResponseBuilder<String>()
            .httpStatus(HttpStatus.NOT_FOUND)
            .httpResultMsg(HttpStatus.NOT_FOUND.getReasonPhrase())
            .customResultCode(CustomResultCode.RESPONSE_USER_NOT_FOUND.getResultCode())
            .customResultMsg(CustomResultCode.RESPONSE_USER_NOT_FOUND.getResultMsg())
            .data("유저정보를 찾을 수 없습니다.(userId : " + userSeqId.toString() + ")")
            .build();
    }
}
