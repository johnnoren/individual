package com.example.ciditest;

import java.time.LocalDateTime;

public class Message {

    private String username;
    private String message;

    private LocalDateTime timestamp;

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }


    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
