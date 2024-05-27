package com.pms.projectmanagementsystem.service;

import com.pms.projectmanagementsystem.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {
    public void sendInvitation(String email, Long projectId) throws MessagingException;
    public Invitation acceptInvitation(String token,Long userId) throws Exception;
    public String getTokenByUserMail(String UserEmail);
    public void deleteToken(String token);

}
