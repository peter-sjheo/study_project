package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService extends EntityAccessService<UserDTO, User> {
    
    public User createUser(UserDTO userDto);    
    public User updateUser(Long userSeqId, UserDTO userDto);

    public User getUser(Long userSeqId);
    public void deleteUser(Long userSeqId);
    
}
