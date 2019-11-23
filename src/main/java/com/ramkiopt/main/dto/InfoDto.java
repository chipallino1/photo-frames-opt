package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class InfoDto implements Serializable {
    private List<NotificationDto> notificationDtos;
    private Integer statusCode;
    private Timestamp timestamp;

    public List<NotificationDto> getNotificationDtos() {
        return notificationDtos;
    }

    public void setNotificationDtos(List<NotificationDto> notificationDtos) {
        this.notificationDtos = notificationDtos;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
