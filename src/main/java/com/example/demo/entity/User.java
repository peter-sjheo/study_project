package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="USERS")
@ToString(exclude = "userGroup")
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

    public User(UserBuilder builder) {
        this.userSeqId      = builder.userSeqId;
        this.userLoginId    = builder.userLoginId;
        this.password       = builder.password;
        this.userName       = builder.userName;
        this.userGroup      = builder.userGroup;
    }

    public User(String userLoginId, String password, String userName, UserGroup userGroup) {        
        this.userLoginId = userLoginId;
        this.password = password;
        this.userName = userName;
        this.userGroup = userGroup;        
    }

    public User(Long _id, String userLoginId, String password, String userName, UserGroup userGroup) {        
        this.userSeqId = _id;
        this.userLoginId = userLoginId;
        this.password = password;
        this.userName = userName;
        this.userGroup = userGroup;        
    }

    public User() {
        
    }




    public static class UserBuilder {

        
        private Long userSeqId;

        
        private String userLoginId;

        
        private String password;

        
        private String userName;    
        
        
        private UserGroup userGroup;
 
        // required parameters        		        
        public UserBuilder userSeqId(Long userSeqId) {
            this.userSeqId = userSeqId;
            return this;
        }


        public UserBuilder userLoginId(String userLoginId) {
            this.userLoginId = userLoginId;
            return this;
        }
 
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder userGroup(UserGroup userGroup) {
            this.userGroup = userGroup;
            return this;
        }
		
        public User build(){
            return new User(this);
        }
 
    }
}
