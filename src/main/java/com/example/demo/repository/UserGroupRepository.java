package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    //UserGroup save(UserGroup requestUserGroupInfo);
    
}
