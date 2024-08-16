package com.praveen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    
}
