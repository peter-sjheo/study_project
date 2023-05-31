package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.annotation.MethodLogger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {
    
    private final UserServiceImpl userServiceImpl;
        
    @PostMapping("/user") /* user 생성 */
    @MethodLogger
    public ResponseEntity<BaseApiResponse<User>> createUserInfo(@RequestBody UserDTO requestUserDto) {    

        User createdUser = userServiceImpl.createUser(requestUserDto);

        final BaseApiResponse<User> baseApiResonse = BaseApiResponse.createSuccessApiResponseByBuilder(createdUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);                        
        return responseEntity;
    }


    
    @GetMapping("/user/{userSeqId}")
    @MethodLogger
    public ResponseEntity<BaseApiResponse<User>> getUserInfo(@PathVariable("userSeqId") Long userSeqId) {    
       
        User user = userServiceImpl.getUser(userSeqId);
       
        /* BUILDER로 할 경우 직렬화에서 오류발생함.. */
        final BaseApiResponse<User> baseApiResonse = BaseApiResponse.createSuccessApiResponseByBuilder(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);        

        log.info("[GET-end] getUserInfo::user result : {}", user);
        return responseEntity;
    }

    
    @PutMapping("/user/{userSeqId}")
    @MethodLogger
    public ResponseEntity<BaseApiResponse<User>> updateUserInfo(@PathVariable("userSeqId") Long userSeqId, @RequestBody UserDTO requestUserDto) {    
        
        User updatedUser = userServiceImpl.updateUser(userSeqId, requestUserDto);        

        final BaseApiResponse<User> baseApiResonse = BaseApiResponse.createSuccessApiResponseByBuilder(updatedUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);                
        return responseEntity;
    }
    
    @DeleteMapping("/user/{userSeqId}")
    @MethodLogger
    public ResponseEntity<BaseApiResponse<Long>> deleteUserInfo(@PathVariable("userSeqId") Long userSeqId) {    
        
        userServiceImpl.deleteUser(userSeqId);        

        final BaseApiResponse<Long> baseApiResonse = BaseApiResponse.createSuccessApiResponseByBuilder(userSeqId);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<Long>> responseEntity = new ResponseEntity<BaseApiResponse<Long>>(baseApiResonse, httpHeaders, HttpStatus.OK);            
        return responseEntity;
    }

}
