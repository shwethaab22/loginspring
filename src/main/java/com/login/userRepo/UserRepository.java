package com.login.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.login.entity.user;

public interface UserRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
    
    
    @Query(value="select * from user where email=?1 and password=?2",nativeQuery=true)
    public user loginvalidation(String email,String password);
    
}
