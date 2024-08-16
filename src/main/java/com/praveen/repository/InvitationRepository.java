package com.praveen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.model.Invitation;


public interface InvitationRepository extends JpaRepository<Invitation, Long>{
    Invitation findByToken(String token);

    Invitation findByEmail (String userEmail);
    
}
