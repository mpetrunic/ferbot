package com.mpetrunic.ferbot.services.routing;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.messages.ResponseMessage;
import com.mpetrunic.ferbot.services.routing.routes.FullFacultyNameAction;
import com.mpetrunic.ferbot.services.routing.routes.HelpAction;
import com.mpetrunic.ferbot.services.routing.routes.WelcomeAction;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ChatbotRouteContainer {

    private Map<Pattern, ResponseAction<IChatDriver, IncomingFacebookMessage>> routes = new LinkedHashMap<>();

    public ChatbotRouteContainer() {
        register("HELLO", new WelcomeAction());
        register("FULL_NAME", new FullFacultyNameAction());
        register("SHOURTCUT_EXPLANATION", "*FER* je kratica za Fakultet elektrotehnike i računarstva!");
        register("HELP", new HelpAction());
    }

    public void register(String pattern, ResponseAction<IChatDriver, IncomingFacebookMessage> action) {
        this.routes.put(Pattern.compile(pattern), action);
    }

    public void register(String pattern, String responseText) {
        this.register(pattern, (driver, incomingFacebookMessage) -> {
            driver.typesAndWaits(incomingFacebookMessage);
            driver.sendResponse(new ResponseMessage(incomingFacebookMessage.getRecipient(), incomingFacebookMessage.getSender(), responseText));
        });
    }

    public Map<Pattern, ResponseAction<IChatDriver, IncomingFacebookMessage>> getRoutes() {
        return routes;
    }

    public ResponseAction<IChatDriver, IncomingFacebookMessage> getFallback() {
        return (driver, message) -> {
            driver.sendResponse(new ResponseMessage(message.getRecipient(), message.getSender(), "Oprosti nisam te razumio :("));
        };
    }
}
