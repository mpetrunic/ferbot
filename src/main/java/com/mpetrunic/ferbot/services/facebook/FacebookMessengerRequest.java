package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public abstract class FacebookMessengerRequest {

    @Key("messaging_type")
    private String messageType = "RESPONSE";

    @Key
    private Recipient recipient;

    public FacebookMessengerRequest(String recipient) {
        this.recipient = new Recipient(recipient);
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

}
