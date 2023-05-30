package com.example.demo.constant.http;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseApiResponse<T> {
        
    private HttpStatus httpStatus;
    private String httpResultMsg;

    private String customResultCode;    
    private String customResultMsg;   

    private T data;

    public static <T> BaseApiResponse<T> createSuccessApiResponse(T data) {
        BaseApiResponse<T> baseApiResonse = new BaseApiResponse<>();
        baseApiResonse.setData(data);
        baseApiResonse.setHttpStatus(HttpStatus.OK);
        baseApiResonse.setHttpResultMsg(HttpStatus.OK.getReasonPhrase());
        baseApiResonse.setCustomResultCode(CustomResultCode.RESPONSE_SUCCESS.getResultCode());
        baseApiResonse.setCustomResultMsg(CustomResultCode.RESPONSE_SUCCESS.getResultMsg());
        return baseApiResonse;
    }
}
