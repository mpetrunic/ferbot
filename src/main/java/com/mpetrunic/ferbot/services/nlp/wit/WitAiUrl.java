package com.mpetrunic.ferbot.services.nlp.wit;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class WitAiUrl extends GenericUrl {

    public WitAiUrl(String url) {
        super(url);
    }

    @Key("v")
    public String version = "20171223";

    @Key("q")
    public String query;
}
