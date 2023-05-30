package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.constant.http.CustomResultCode;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private BaseApiResponse<String> apiResponse;
    
    public UserNotFoundException(Long userSeqId) {
        apiResponse = new BaseApiResponse<>();
        apiResponse.setCustomResultCode(CustomResultCode.RESPONSE_USER_NOT_FOUND.getResultCode());
        apiResponse.setCustomResultMsg(CustomResultCode.RESPONSE_USER_NOT_FOUND.getResultMsg());
        apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        apiResponse.setHttpResultMsg(HttpStatus.NOT_FOUND.getReasonPhrase());
        apiResponse.setData("유저정보를 찾을 수 없습니다.(userId : " + userSeqId.toString() + ")");        
    }
}
