package com.example.demo.entity;

import com.example.demo.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Table(name="USERS")
@ToString(exclude = "userGroup")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_SEQ_ID")
    private Long userSeqId;

    @Column(name="USER_LOGIN_ID")
    private String userLoginId;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="USER_NAME")
    private String userName;    
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_GROUP_SEQ_ID")    
    private UserGroup userGroup;    


    public void updateUserInfo(UserDTO userDto) {
        this.userLoginId    = userDto.getUserLoginId();
        this.password       = userDto.getPassword();
        this.userName       = userDto.getUserName();
    }
}
