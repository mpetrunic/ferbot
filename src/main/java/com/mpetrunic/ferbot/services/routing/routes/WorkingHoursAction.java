package com.mpetrunic.ferbot.services.routing.routes;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseFactory;
import com.mpetrunic.ferbot.services.routing.ResponseAction;

import java.util.HashMap;
import java.util.Map;

public class WorkingHoursAction implements ResponseAction<IChatDriver, IncomingFacebookMessage> {

    private Map<String, String> workingHours = new HashMap<String, String>() {{
        put("FER", "00-24h");
        put("STUSLU", "12-14h");
        put("KNJIŽ", "08-19h");
    }};


    @Override
    public void apply(IChatDriver driver, IncomingFacebookMessage message) {
        driver.typesAndWaits(message);
        String component = "FER";
        if (message.getExtras().getEntities().containsKey("FACULTY_COMPONENT")) {
            component = message.getExtras().getEntities().get("FACULTY_COMPONENT").getValue();
            if (!workingHours.containsKey(component)) {
                component = "FER";
            }
        }
        driver.sendResponse(ResponseFactory.simpleMessage(
                message,
                "Radno vrijeme " + component
                        + " je "
                        + workingHours.getOrDefault(component, "nepoznato")
        ));
    }
}
