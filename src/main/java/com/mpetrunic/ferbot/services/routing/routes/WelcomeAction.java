package com.mpetrunic.ferbot.services.routing.routes;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseMessage;
import com.mpetrunic.ferbot.services.routing.ResponseAction;

public class WelcomeAction implements ResponseAction<IChatDriver, IncomingFacebookMessage> {
    @Override
    public void apply(IChatDriver driver, IncomingFacebookMessage message) {
        driver.typesAndWaits(message);
        driver.sendResponse(new ResponseMessage(message.getRecipient(), message.getSender(), "Bok"));
        driver.typesAndWaits(message);
        driver.sendResponse(
                new ResponseMessage(
                        message.getRecipient(),
                        message.getSender(),
                        "Ja sam *FERbot* i nadam se da ću moći odgovoriti na sva tvoja pitanja!"
                )
        );
    }
}
