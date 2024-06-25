package com.pms.projectmanagementsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Join Project Team Invitation";
        String text = "Click the link to join the project team: " + link;

        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
            LOGGER.info("Email sent successfully to " + userEmail);
        } catch (MailSendException e) {
            LOGGER.log(Level.SEVERE, "MailSendException: Error sending email to " + userEmail, e);
            throw new MailSendException("Error sending email to " + userEmail, e);
        } catch (MailException e) {
            LOGGER.log(Level.SEVERE, "MailException: Error sending email to " + userEmail, e);
            throw new MessagingException("Error sending email to " + userEmail, e);
        }
    }
}
