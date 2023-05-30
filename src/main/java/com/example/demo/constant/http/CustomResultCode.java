package com.example.demo.constant.http;

import lombok.Getter;

@Getter
public enum CustomResultCode {
        
    RESPONSE_SUCCESS("0000", "SUCCESS"), 
    
    RESPONSE_USER_NOT_FOUND("5000", "User not Found"),

    RESPONSE_USER_NOT_DELETED("5001", "User not Deleted"),

    RESPONSE_FAIL("9999", "FAIL");


    private String resultCode;
    private String resultMsg;

    CustomResultCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }    
}
