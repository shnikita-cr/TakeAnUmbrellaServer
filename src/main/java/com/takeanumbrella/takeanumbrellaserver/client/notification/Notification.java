package com.takeanumbrella.takeanumbrellaserver.client.notification;

public class Notification {
    private Long messageId;
    private String message;

    public Notification(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getMessageId() {
        return messageId;
    }
}
