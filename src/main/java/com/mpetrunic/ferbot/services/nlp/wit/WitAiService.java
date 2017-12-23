package com.mpetrunic.ferbot.services.nlp.wit;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class WitAiService {

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static JsonFactory JSON_FACTORY = new JacksonFactory();

    private HttpRequestFactory requestFactory;

    private String url;

    private String accessToken;

    @PostConstruct
    public void init() {
        requestFactory = HTTP_TRANSPORT.createRequestFactory(
                request -> {
                    request.setParser(JSON_FACTORY.createJsonObjectParser());
                    HttpHeaders headers = request.getHeaders();
                    headers.setAuthorization("Bearer " + this.accessToken);
                }
        );
    }

    public WitAiService(
            @Value("${ferbot.wit.url}") String url,
            @Value("${ferbot.wit.access_token}") String accessToken) {
        this.url = url;
        this.accessToken = accessToken;
    }

    public WitResponse processInput(String input) throws IOException {
        HttpRequest request = requestFactory.buildGetRequest(getUrl(input));
        return request.execute().parseAs(WitResponse.class);
    }

    private WitAiUrl getUrl(String query) {
        WitAiUrl url = new WitAiUrl(this.url);
        url.query = query;
        return url;
    }
}
