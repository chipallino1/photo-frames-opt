package com.ramkiopt.main.dto;

import com.ramkiopt.main.services.app.base.NotificationType;

import java.io.Serializable;

public class NotificationDto implements Serializable {
    private NotificationType type;

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
