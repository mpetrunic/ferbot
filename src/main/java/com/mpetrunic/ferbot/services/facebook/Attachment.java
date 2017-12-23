package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class Attachment {

    @Key
    private String type;

    @Key
    private Template payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Template getPayload() {
        return payload;
    }

    public void setPayload(Template payload) {
        this.payload = payload;
    }
}
