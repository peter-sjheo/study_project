package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.MethodLogger;
import com.example.demo.constant.http.BaseApiResponse;
import com.example.demo.entity.UserGroup;
import com.example.demo.repository.UserGroupRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class UserGroupController {

    private final UserGroupRepository userGroupRepository;
    
    @PostMapping("/user-group") /* userGroup 생성 */
    @MethodLogger
    public ResponseEntity<BaseApiResponse<UserGroup>> saveUserGroupInfo(@RequestBody UserGroup requestUserGroupInfo) {

        log.info("start");
        UserGroup savedUserGroup = userGroupRepository.save(requestUserGroupInfo);

        
        final BaseApiResponse<UserGroup> baseApiResonse = BaseApiResponse.createSuccessApiResponseByBuilder(savedUserGroup);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<BaseApiResponse<UserGroup>> responseEntity = new ResponseEntity<BaseApiResponse<UserGroup>>(baseApiResonse, httpHeaders, HttpStatus.OK);

        log.info("end");
        return responseEntity;
    }
}
