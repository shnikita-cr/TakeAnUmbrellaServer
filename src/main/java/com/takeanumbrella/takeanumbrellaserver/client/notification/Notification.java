package com.takeanumbrella.takeanumbrellaserver.client.notification;

import com.takeanumbrella.takeanumbrellaserver.client.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @Column(name = "message", nullable = false, length = 500)
    private String message;

    protected Notification() {
    }

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
