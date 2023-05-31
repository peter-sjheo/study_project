package com.example.demo.constant.http;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseApiResponse<T> {
        
    private HttpStatus httpStatus;
    private String httpResultMsg;

    private String customResultCode;    
    private String customResultMsg;   

    private T data;
    
    public static <T> BaseApiResponse<T> createSuccessApiResponseByBuilder(T data) {
        BaseApiResponse<T> baseApiResonse = new BaseApiResponseBuilder<T>()
            .httpStatus(HttpStatus.OK)
            .httpResultMsg(HttpStatus.OK.getReasonPhrase())
            .customResultCode(CustomResultCode.RESPONSE_SUCCESS.getResultCode())
            .customResultMsg(CustomResultCode.RESPONSE_SUCCESS.getResultMsg())
            .data(data)
            .build();
                    
        return baseApiResonse;
    }

    public static class BaseApiResponseBuilder<T> {
        private BaseApiResponse<T> baseApiResonse;

        public BaseApiResponseBuilder<T> httpStatus(HttpStatus httpStatus) {
            this.baseApiResonse.httpStatus = httpStatus;
            return this;
        }

        public BaseApiResponseBuilder<T> httpResultMsg(String httpResultMsg) {
            this.baseApiResonse.httpResultMsg = httpResultMsg;
            return this;
        }

        public BaseApiResponseBuilder<T> customResultCode(String customResultCode) {
            this.baseApiResonse.customResultCode = customResultCode;
            return this;
        }

        public BaseApiResponseBuilder<T> customResultMsg(String customResultMsg) {
            this.baseApiResonse.customResultMsg = customResultMsg;
            return this;
        }

        public BaseApiResponseBuilder<T> data(T data) {
            this.baseApiResonse.data = data;
            return this;
        }

        public BaseApiResponse<T> build() {
            return baseApiResonse;
        }
    }
}
