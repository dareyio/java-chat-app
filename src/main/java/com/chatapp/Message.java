package com.chatapp;

import java.io.Serializable;

public class Message implements Serializable {
    private final String sender;
    private final String receiver;
    private final String content;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return sender + " to " + receiver + ": " + content;
    }
}
