package com.example.ciditest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Messages {
    private static List<Message> messageList = new ArrayList<>();
    public static void addMessage(Message message) {
        messageList.add(message);
    }
    public static List<Message> getAllMessages() {
        return messageList;
    }

    public static List<Message> getAllMessagesAfterTimestamp(LocalDateTime timestamp) {
        return messageList.stream().filter(message -> message.getTimestamp().isAfter(timestamp)).collect(Collectors.toList());
    }

    public static void clearMessages() {
        messageList.clear();
    }
}
