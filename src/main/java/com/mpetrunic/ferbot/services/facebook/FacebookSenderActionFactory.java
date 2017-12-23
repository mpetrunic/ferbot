package com.mpetrunic.ferbot.services.facebook;

public class FacebookSenderActionFactory {

    public static FacebookSenderAction seenRequest(String recipient) {
        return new FacebookSenderAction(recipient, "mark_seen");
    }

    public static FacebookSenderAction typingOnRequest(String recipient) {
        return new FacebookSenderAction(recipient, "typing_on");
    }

    public static FacebookSenderAction typingOffRequest(String recipient) {
        return new FacebookSenderAction(recipient, "typing_off");
    }

}
