package com.mpetrunic.ferbot.services.routing;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

@Service
public class ChatbotRouter {

    private ChatbotRouteContainer routeContainer;

    @Autowired
    public ChatbotRouter(ChatbotRouteContainer routeContainer) {
        this.routeContainer = routeContainer;
    }

    public void handleMessage(IChatDriver driver, IncomingFacebookMessage message) {
        this.routeContainer.getRoutes().forEach((pattern, responseAction) -> {
            String intent = message.getExtras().getIntent();
            Matcher m = pattern.matcher(message.getText());
            if (pattern.pattern().equals(intent) || m.matches()) {
                responseAction.apply(driver, message);
            }
        });
    }
}
