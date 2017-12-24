package com.mpetrunic.ferbot.services.routing.routes;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.FacebookDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.facebook.ButtonTemplate;
import com.mpetrunic.ferbot.services.facebook.FacebookButtonFactory;
import com.mpetrunic.ferbot.services.routing.ResponseAction;

public class HelpAction implements ResponseAction<IChatDriver, IncomingFacebookMessage> {

    @Override
    public void apply(IChatDriver driver, IncomingFacebookMessage message) {
        driver.typesAndWaits(message);
        if (driver instanceof FacebookDriver) {
            ((FacebookDriver) driver).sendResponse(
                    new ButtonTemplate("Ovo su neke stvari s kojima ti mogu pomoći:")
                            .addButton(FacebookButtonFactory.postback("Ime fakulteta?", "FULL_NAME"))
                            .addButton(FacebookButtonFactory.postback("Radno vrijeme?", "WORKING_HOURS"))
                            .build(message.getSender())
            );
        }
    }

}
