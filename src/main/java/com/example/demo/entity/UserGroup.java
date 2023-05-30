package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="USER_GROUP")
public class UserGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_GROUP_SEQ_ID")
    private Long userGroupSeqId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();

    @JsonProperty("users")
    public List<User> getUsers() {        
        return null;
    }

    public UserGroup(Long userGroupSeqId) {
        this.userGroupSeqId = userGroupSeqId;
    }

    public UserGroup() {
        
    }

    public void addUser(User user) {
        this.userList.add(user);        
    }

    @Override
    public String toString() {
        String text = "userGroupId: " + userGroupSeqId.toString();
        if(userList != null) {
            for(User user : userList) {
                text += user.toString();
            }
        }

        return text;
    }

}
