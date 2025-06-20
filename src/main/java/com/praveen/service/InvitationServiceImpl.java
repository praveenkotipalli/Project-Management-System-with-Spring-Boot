package com.praveen.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.model.Invitation;
import com.praveen.repository.InvitationRepository;

import jakarta.mail.MessagingException;

// import jakarta.mail.MessagingException;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitaion(String email, Long projectId){
        try {
        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        String invitationLink = "https://project-management-system-with-spring-7svx.onrender.com/api/projects/accept_invitation?token=" + invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    } catch (MessagingException e) {
        throw new RuntimeException("Failed to send invitation email", e);
    }
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation == null){
            throw new Exception("Invalid invitation token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
       Invitation invitation = invitationRepository.findByEmail(userEmail);
       return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepository.findByToken(token);

        invitationRepository.delete(invitation);
    }
    
}
