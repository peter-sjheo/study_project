package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.constant.http.CustomResultCode;

public class UserNotDeletedException extends RuntimeException {
    private BaseApiResponse<String> apiResponse;

    public UserNotDeletedException(Long userSeqId) {
        apiResponse = new BaseApiResponse<>();
        apiResponse.setCustomResultCode(CustomResultCode.RESPONSE_USER_NOT_DELETED.getResultCode());
        apiResponse.setCustomResultMsg(CustomResultCode.RESPONSE_USER_NOT_DELETED.getResultMsg());
        apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        apiResponse.setHttpResultMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        apiResponse.setData("유저정보를 삭제하지 못했습니다.(userId : " + userSeqId.toString() + ")");        
    }
}
