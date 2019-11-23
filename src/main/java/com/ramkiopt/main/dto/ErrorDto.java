package com.ramkiopt.main.dto;

public class ErrorDto extends NotificationDto {
    private String message;
    private String advice;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
