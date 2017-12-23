package com.mpetrunic.ferbot.services.drivers.impl.facebook;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.facebook.FacebookMessenger;
import com.mpetrunic.ferbot.services.facebook.FacebookSenderActionFactory;
import com.mpetrunic.ferbot.services.facebook.OutgoingFacebookMessage;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class FacebookDriver implements IChatDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(FacebookDriver.class);

    private String verification;

    private String secret;

    private FacebookMessenger messenger;

    private ChatbotRouter router;

    @Autowired
    public FacebookDriver(
            @Value("${ferbot.facebook.verification}") String verification,
            @Value("${ferbot.facebook.secret}") String secret,
            FacebookMessenger messenger,
            ChatbotRouter router
    ) {
        this.verification = verification;
        this.secret = secret;
        this.messenger = messenger;
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
            this.markSeen(message);
            LOGGER.info(message.toString());
            this.router.handleMessage(this, message);
        }
    }

    @Override
    public void markSeen(IncomingFacebookMessage message) {
        try {
            this.messenger.sendMessage(FacebookSenderActionFactory.seenRequest(message.getSender()));
        } catch (IOException e) {
            LOGGER.error("Failed to mark message as seen");
        }
    }

    @Override
    public void types(IncomingFacebookMessage message) {
        try {
            this.messenger.sendMessage(FacebookSenderActionFactory.typingOnRequest(message.getSender()));
        } catch (IOException e) {
            LOGGER.error("Failed to turn on typing");
        }
    }

    @Override
    public void typesAndWaits(IncomingFacebookMessage message) {
        try {
            this.messenger.sendMessage(FacebookSenderActionFactory.typingOnRequest(message.getSender()));
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(300) + 300);
        } catch (IOException e) {
            LOGGER.error("Failed to turn on typing");
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void sendResponse(ResponseMessage responseMessage) {
        LOGGER.info("Sending response -> {}", responseMessage);
        try {
            messenger.sendMessage(new OutgoingFacebookMessage(responseMessage.getRecipient(), responseMessage.getText()));
        } catch (IOException e) {
            LOGGER.error("Failed to send message to facebook messenger", e);
        }
    }

}
