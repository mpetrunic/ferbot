package com.mpetrunic.ferbot.services.routing;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;

@Service
public class ChatbotRouter {

    private ChatbotRouteContainer routeContainer;

    @Autowired
    public ChatbotRouter(ChatbotRouteContainer routeContainer) {
        this.routeContainer = routeContainer;
    }

    public void handleMessage(IChatDriver driver, IncomingFacebookMessage message) {
        ResponseAction<IChatDriver, IncomingFacebookMessage> action =
                this.routeContainer.getRoutes().entrySet().stream().filter(entry -> {
            String intent = message.getExtras().getIntent();
                    Matcher m = entry.getKey().matcher(message.getText());
                    return entry.getKey().pattern().equals(intent) || m.matches();
                }).map(Map.Entry::getValue).findFirst().orElse(routeContainer.getFallback());
        action.apply(driver, message);
    }
}
