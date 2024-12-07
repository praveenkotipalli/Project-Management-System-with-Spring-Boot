package com.praveen.service;

import com.praveen.model.Invitation;

// import jakarta.mail.MessagingException;

public interface InvitationService {
    public void sendInvitaion(String email, Long projectId);
    public Invitation acceptInvitation (String token, Long userId) throws Exception;

    public String getTokenByUserMail (String userEmail);

    void deleteToken(String token);
}
