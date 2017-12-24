package com.mpetrunic.ferbot.services.routing.routes;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseFactory;
import com.mpetrunic.ferbot.services.routing.ResponseAction;

public class FullFacultyNameAction implements ResponseAction<IChatDriver, IncomingFacebookMessage> {
    @Override
    public void apply(IChatDriver driver, IncomingFacebookMessage message) {
        driver.typesAndWaits(message);
        driver.sendResponse(
                ResponseFactory.simpleMessage(
                        message,
                        "Puno ime fakulteta je *Fakultet elektrotehnike i računarstva*!"
                )
        );
    }
}
