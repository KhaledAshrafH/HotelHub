package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.entity.Notification;

public interface EmailNotificationService {
    void sendEmail(String to, String subject, String body);
    void sendNotificationEmail(Notification notification);
}
