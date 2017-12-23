package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class FacebookSenderAction extends FacebookMessengerRequest {

    @Key("sender_action")
    private String action;

    public FacebookSenderAction(String recipient, String action) {
        super(recipient);
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
