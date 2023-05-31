package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.dto.UserLoginDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserLoginController {
    
    @PostMapping("/user-login")
    public ResponseEntity<BaseApiResponse<String>> userLogin(@RequestBody UserLoginDTO userLoginInfo) {
        log.info("ddd");
        return null;
    }

}
