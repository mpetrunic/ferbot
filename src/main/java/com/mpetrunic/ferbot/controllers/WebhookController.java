package com.mpetrunic.ferbot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebhookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

    @GetMapping("/api/v1/webhook")
    @PostMapping("/api/v1/webhook")
    public ResponseEntity<String> webhook(
            @RequestHeader Map<String, String> headers,
            @RequestParam HashMap<String, Object> request) {
        try {
            LOGGER.info(headers.toString());
            LOGGER.info(request.toString());
        } catch (Exception e) {
            LOGGER.error("Failed to process incomming event request", e);
        }
        return new ResponseEntity<>("EVENT_RECEIVED", HttpStatus.OK);
    }

}
