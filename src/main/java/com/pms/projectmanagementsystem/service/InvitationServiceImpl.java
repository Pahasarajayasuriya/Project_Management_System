package com.pms.projectmanagementsystem.service;

import com.pms.projectmanagementsystem.model.Invitation;
import com.pms.projectmanagementsystem.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private InvitationRepository invitationRepository;
    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {
        String InvitationToken = UUID.randomUUID().toString();
        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(InvitationToken);

        invitationRepository.save(invitation);
        String link = "http://localhost:5173/accept_invitation?token="+InvitationToken;
        emailService.sendEmailWithToken(email,link);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation == null){
            throw new Exception("Invalid Invitation Token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String UserEmail) {
        Invitation invitation = invitationRepository.findByEmail(UserEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {
        Invitation invitation = invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
