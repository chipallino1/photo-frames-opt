package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.util.List;

public class InfoDto implements Serializable {
    private List<MessageInfoDto> messages;
    private Integer statusCode;

    public List<MessageInfoDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageInfoDto> messages) {
        this.messages = messages;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
