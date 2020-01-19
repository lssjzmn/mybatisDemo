package com.example.mydemo.core.bo;

import java.io.Serializable;

public class WebMessagePayload implements Serializable {

    private String messageName;

    private String messageSerialNo;

    private Object payload;

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageSerialNo() {
        return messageSerialNo;
    }

    public void setMessageSerialNo(String messageSerialNo) {
        this.messageSerialNo = messageSerialNo;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "msg {" +
                "Name='" + messageName + '\'' +
                ", SerialNo='" + messageSerialNo + '\'' +
                ", payload=" + payload +
                '}';
    }
}
