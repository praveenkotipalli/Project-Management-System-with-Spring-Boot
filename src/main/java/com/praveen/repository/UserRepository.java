package com.praveen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.model.User;



public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    
}
