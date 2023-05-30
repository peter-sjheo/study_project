package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    /*
    Optional<User> findById(Long userSeqId);

    boolean existsById(Long userSeqId);

    User save(User requestUserInfo);

    void deleteById(Long userSeqId);    
    */

}
