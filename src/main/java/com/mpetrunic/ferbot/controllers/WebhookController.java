package com.mpetrunic.ferbot.controllers;

import com.mpetrunic.ferbot.services.drivers.IChatDriver;
import com.mpetrunic.ferbot.services.middleware.impl.WitAiMiddleware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebhookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

    private IChatDriver driver;

    @Autowired
    public WebhookController(IChatDriver driver, WitAiMiddleware middleware) {
        this.driver = driver;
        this.driver.addMiddleware(middleware);
    }

    @RequestMapping(path = "/api/v1/webhook", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Object> webhook(
            @RequestHeader Map<String, String> headers,
            @RequestParam HashMap<String, Object> params,
            @RequestBody(required = false) HashMap<String, Object> request) {
        try {
            if(request != null) {
                params.putAll(request);
            }
            ResponseEntity<Object> verifyResult = driver.verifyRequest(headers, params);
            if(verifyResult != null) {
                return verifyResult;
            }
            this.driver.handleMessages(headers, params);
        } catch (Exception e) {
            LOGGER.error("Failed to process incomming event request", e);
        }
        return new ResponseEntity<>("EVENT_RECEIVED", HttpStatus.OK);
    }

}
