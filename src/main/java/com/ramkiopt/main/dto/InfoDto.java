package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.util.List;

public class InfoDto implements Serializable {
    private List<String> messages;
    private Integer statusCode;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
