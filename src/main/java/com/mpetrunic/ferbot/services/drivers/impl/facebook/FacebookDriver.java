package com.mpetrunic.ferbot.services.drivers.impl.facebook;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.messages.ResponseMessage;
import com.mpetrunic.ferbot.services.routing.ChatbotRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FacebookDriver implements IChatDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookDriver.class);

    private String verification;

    private String secret;

    private String accessToken;

    private ChatbotRouter router;

    @Autowired
    public FacebookDriver(
            @Value("${ferbot.facebook.verification}") String verification,
            @Value("${ferbot.facebook.secret}") String secret,
            @Value("${ferbot.facebook.page.access_token}") String accessToken,
            ChatbotRouter router
    ) {
        this.verification = verification;
        this.secret = secret;
        this.accessToken = accessToken;
        this.router = router;
    }

    @Override
    public ResponseEntity<Object> verifyRequest(Map<String, String> headers, Map<String, Object> params) {
        if(!isVerifyRequest(params)) return null;
        return verify(params);
    }

    private boolean isVerifyRequest(Map<String, Object> params) {
        return params.getOrDefault("hub.mode", "").equals("subscribe");
    }

    private ResponseEntity<Object> verify(Map<String, Object> params) {
        if (params.getOrDefault("hub.verify_token", "").equals(verification)) {
            return new ResponseEntity<>(
                    params.getOrDefault("hub.challenge", "Challenge not sent"),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                "Verification doesn't match",
                HttpStatus.OK
        );
    }

    @Override
    public boolean isRequestValid(Map<String, String> headers, Map<String, Object> request) {
        return true;
    }

    @Async
    @SuppressWarnings("unchecked")
    @Override
    public void handleMessages(Map<String, String> headers, Map<String, Object> params) {
        Map<String, Object> entry = ((List<Map<String, Object>>) params.get("entry")).get(0);
        for(Map<String, Object> messageParams :  (ArrayList<Map<String, Object>>)entry.getOrDefault("messaging", new ArrayList<>())) {
            IncomingFacebookMessage message = new IncomingFacebookMessage(messageParams);
            LOGGER.info(message.toString());
            this.router.handleMessage(this, message);
        }
    }

    @Override
    public void sendResponse(ResponseMessage responseMessage) {
        LOGGER.info("Sending response -> {}", responseMessage);
    }

}
