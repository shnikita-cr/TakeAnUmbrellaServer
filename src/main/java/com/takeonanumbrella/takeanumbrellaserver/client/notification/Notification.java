package com.takeonanumbrella.takeanumbrellaserver.client.notification;

public class Notification {
    private Long idMessage;
    private String message;

    public Notification(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getIdMessage() {
        return idMessage;
    }
}
