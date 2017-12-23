package com.mpetrunic.ferbot.services.drivers;

import com.mpetrunic.ferbot.services.messages.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IChatDriver {

    ResponseEntity<Object> verifyRequest(Map<String, String> headers, Map<String, Object> params);

    boolean isRequestValid(Map<String, String> headers, Map<String, Object> request);

    void handleMessages(Map<String, String> headers, Map<String, Object> params);

    void sendResponse(ResponseMessage responseMessage);
}
