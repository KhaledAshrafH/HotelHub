package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.model.entity.Notification;
import com.khalouda.hotelhub.service.EmailNotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationServiceImpl implements EmailNotificationService {
    private final JavaMailSender mailSender;


    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void sendNotificationEmail(Notification notification) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setContent(notification.getMessage(), "text/html");
            message.setSubject("HotelHub Notification");
            message.setFrom("hotelhuborg@gmail.com");
//            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(notification.getUser().getEmail()));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("5aleda4rf@gmail.com")); // for test only
            mailSender.send(message);
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
