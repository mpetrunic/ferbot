package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class FacebookMessengerUrl extends GenericUrl {

    public FacebookMessengerUrl(String encodedUrl) {
        super(encodedUrl);
    }

    @Key("access_token")
    public String accessToken;

}
