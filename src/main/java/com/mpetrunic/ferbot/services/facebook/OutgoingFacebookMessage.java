package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class OutgoingFacebookMessage extends FacebookMessengerRequest {

    @Key
    private Message message;


    public OutgoingFacebookMessage(String recipient, Message message) {
        super(recipient);
        this.message = message;
    }

    public OutgoingFacebookMessage(String recipient, String message) {
        this(recipient, new Message(message));
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}