package com.mpetrunic.ferbot.services.routing.routes;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseFactory;
import com.mpetrunic.ferbot.services.routing.ResponseAction;

public class WelcomeAction implements ResponseAction<IChatDriver, IncomingFacebookMessage> {
    @Override
    public void apply(IChatDriver driver, IncomingFacebookMessage message) {
        driver.typesAndWaits(message);
        driver.sendResponse(ResponseFactory.simpleMessage(message, "Bok!"));
        driver.typesAndWaits(message);
        driver.sendResponse(
                ResponseFactory.simpleMessage(
                        message,
                        "Ja sam *FERbot* i nadam se da ću moći odgovoriti na sva tvoja pitanja!"
                )
        );
    }
}
