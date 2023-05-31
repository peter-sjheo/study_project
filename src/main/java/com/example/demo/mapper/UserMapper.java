package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userSeqId", ignore=true)
    @Mapping(target = "userGroup", ignore=true)
    User toEntity(UserDTO userDto);
    UserDTO toDto(User user);
}
