package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FacebookMessenger {

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static JsonFactory JSON_FACTORY = new JacksonFactory();

    private HttpRequestFactory requestFactory;

    private FacebookMessengerUrl url;

    public FacebookMessenger(
            @Value("${ferbot.facebook.page.access_token}") String accessToken,
            @Value("${ferbot.facebook.messenger.url}") String url) {
        this.url = new FacebookMessengerUrl(url);
        this.url.accessToken = accessToken;
    }

    @PostConstruct
    public void init() {
        requestFactory = HTTP_TRANSPORT.createRequestFactory(
                request -> request.setParser(JSON_FACTORY.createJsonObjectParser())
        );
    }

    public FacebookMessengerResponse sendMessage(FacebookMessengerRequest message) throws IOException {
        HttpRequest request = requestFactory.buildPostRequest(url, new JsonHttpContent(JSON_FACTORY, message));
        return request.execute().parseAs(FacebookMessengerResponse.class);
    }

}
