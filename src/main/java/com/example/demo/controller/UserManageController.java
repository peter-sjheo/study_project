package com.example.demo.controller;

import static com.example.demo.constant.http.BaseApiResponse.createSuccessApiResponse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.entity.User;
import com.example.demo.entity.UserGroup;
import com.example.demo.entity.User.UserBuilder;
import com.example.demo.exception.UserNotDeletedException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserGroupRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class UserManageController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;


    @PostMapping("/user-group")
    public ResponseEntity<BaseApiResponse<UserGroup>> saveUserGroupInfo(@RequestBody UserGroup requestUserGroupInfo) {
        UserGroup savedUserGroup = userGroupRepository.save(requestUserGroupInfo);

        
        final BaseApiResponse<UserGroup> baseApiResonse = createSuccessApiResponse(savedUserGroup);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<UserGroup>> responseEntity = new ResponseEntity<BaseApiResponse<UserGroup>>(baseApiResonse, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping("/user")
    public ResponseEntity<BaseApiResponse<User>> saveUserInfo(@RequestBody User requestUserInfo) {    
        
        Optional<UserGroup> userGroup = userGroupRepository.findById(requestUserInfo.getUserGroup().getUserGroupSeqId());
        userGroup.get().addUser(requestUserInfo);
        requestUserInfo.setUserGroup(userGroup.get());
        User savedUser = userRepository.save(requestUserInfo);


        final BaseApiResponse<User> baseApiResonse = createSuccessApiResponse(savedUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);                        
        return responseEntity;
    }

    @GetMapping("/user/{userSeqId}")
    public ResponseEntity<BaseApiResponse<User>> getUserInfo(@PathVariable("userSeqId") Long userSeqId) {    
        
        log.info("[GET-start] getUserInfo::userSeqId : {}", userSeqId);

        boolean isExist = userRepository.existsById(userSeqId);
        if(!isExist) {
            throw new UserNotFoundException(userSeqId);
        }
        

        Optional<User> user = userRepository.findById(userSeqId);                
        /* BUILDER로 할 경우 직렬화에서 오류발생함.. */
        final BaseApiResponse<User> baseApiResonse = createSuccessApiResponse(user.get());
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);        

        log.info("[GET-end] getUserInfo::user result : {}", user);
        return responseEntity;
    }


    @PutMapping("/user/{userSeqId}")
    public ResponseEntity<BaseApiResponse<User>> updateUserInfo(@PathVariable("userSeqId") Long userSeqId, @RequestBody User requestUserInfo) {    
        
        log.info("[PUT-start] updateUserInfo::userSeqId : {}, requestUserInfo : {}", userSeqId, requestUserInfo);

        boolean isExist = userRepository.existsById(userSeqId);
        if(!isExist) {
            throw new UserNotFoundException(userSeqId);
        }

        log.info("[start]- user/{userSeqId}(%s) call", userSeqId.toString());
        User user = new UserBuilder()
            .userSeqId(userSeqId)
            .userName(requestUserInfo.getUserName())
            .password(requestUserInfo.getPassword())
            .userLoginId(requestUserInfo.getUserLoginId())
            .userGroup(requestUserInfo.getUserGroup())
            .build();
        
        User savedUser = userRepository.save(user);

        /* BUILDER로 할 경우 직렬화에서 오류발생함.. */
        final BaseApiResponse<User> baseApiResonse = createSuccessApiResponse(savedUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);        

        log.info("[PUT-end] updateUserInfo::UpdatedUser : {}", savedUser);
        return responseEntity;
    }

    @DeleteMapping("/user/{userSeqId}")
    public ResponseEntity<BaseApiResponse<User>> deleteUserInfo(@PathVariable("userSeqId") Long userSeqId) {    
        
        log.info("[DELETE-start] deleteUserInfo::userSeqId : {}", userSeqId);
        boolean isExist = userRepository.existsById(userSeqId);
        if(!isExist) {
            throw new UserNotFoundException(userSeqId);
        }
        
        Optional<User> user = userRepository.findById(userSeqId);                
        userRepository.deleteById(userSeqId);
        isExist = userRepository.existsById(userSeqId);
        if(!isExist) {
            throw new UserNotDeletedException(userSeqId);
        }

        /* BUILDER로 할 경우 직렬화에서 오류발생함.. */
        final BaseApiResponse<User> baseApiResonse = createSuccessApiResponse(user.get());
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<User>> responseEntity = new ResponseEntity<BaseApiResponse<User>>(baseApiResonse, httpHeaders, HttpStatus.OK);
        

        log.info("[DELETE-end] deleteUserInfo::deletedUser : {}", user);
        return responseEntity;
    }
    
}
