package com.ramkiopt.main.dto;

import com.ramkiopt.main.services.utils.MessageInfoType;

import java.io.Serializable;

public class MessageInfoDto implements Serializable {
    private MessageInfoType messageInfoType;
    private Object message;

    public MessageInfoType getMessageInfoType() {
        return messageInfoType;
    }

    public void setMessageInfoType(MessageInfoType messageInfoType) {
        this.messageInfoType = messageInfoType;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
