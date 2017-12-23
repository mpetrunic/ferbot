package com.mpetrunic.ferbot.services.routing;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ChatbotRouteContainer {

    private Map<Pattern, ResponseAction<IChatDriver, IncomingFacebookMessage>> routes = new HashMap<>();

    public ChatbotRouteContainer() {
        register("bok", "Bok i tebi!");
    }

    public void register(String pattern, ResponseAction<IChatDriver, IncomingFacebookMessage> action) {
        this.routes.put(Pattern.compile(pattern), action);
    }

    public void register(String pattern, String responseText) {
        this.register(pattern, (driver, incomingFacebookMessage) -> {
            driver.sendResponse(new ResponseMessage(incomingFacebookMessage.getRecipient(), incomingFacebookMessage.getSender(), responseText));

        });
    }

    public Map<Pattern, ResponseAction<IChatDriver, IncomingFacebookMessage>> getRoutes() {
        return routes;
    }

}
