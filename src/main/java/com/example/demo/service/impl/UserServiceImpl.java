package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.annotation.MethodLogger;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    private final UserRepository userRepository;

    @Override
    @MethodLogger
    public User createUser(UserDTO userDto) {                
        return userRepository.save(this.toEntity(userDto));
    }

    @Override
    @MethodLogger
    public User getUser(Long userSeqId) {
        return userRepository.findById(userSeqId).orElseThrow(() -> new UserNotFoundException(userSeqId));
    }

    @Override
    @MethodLogger
    public User updateUser(Long userSeqId, UserDTO userDto) {
        User user = userRepository.findById(userSeqId).orElseThrow(()-> new UserNotFoundException(userSeqId));
        user.updateUserInfo(userDto);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    @MethodLogger
    public void deleteUser(Long userSeqId) {             
        User user = userRepository.findById(userSeqId).orElseThrow( ()-> new UserNotFoundException(userSeqId));
        userRepository.delete(user);
    }


    @Override
    @MethodLogger
    public User toEntity(UserDTO dto) {    
        return userMapper.toEntity(dto);        
    }

    @Override
    @MethodLogger
    public UserDTO toDto(User user) {
        return userMapper.toDto(user);
    }    
        
}
