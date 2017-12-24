package com.mpetrunic.ferbot.services.messages;

import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;

public class ResponseFactory {

    public static ResponseMessage simpleMessage(IncomingFacebookMessage incomingFacebookMessage, String message) {
        return new ResponseMessage(incomingFacebookMessage.getRecipient(), incomingFacebookMessage.getSender(), message);
    }

}
