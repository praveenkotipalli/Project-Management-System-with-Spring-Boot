package com.praveen.repository;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.praveen.model.Chat;
import com.praveen.model.Message;

public interface MessageRepository  extends JpaRepository<Message, Long>{
    // Optional<Chat> findByChatIdOrderByCreatedAtAsc();

    List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
}
