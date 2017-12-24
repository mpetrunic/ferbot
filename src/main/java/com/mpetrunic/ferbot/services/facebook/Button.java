package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class Button {

    @Key
    private String type;

    @Key
    private String title;

    @Key
    private String payload;

    public Button(String type, String title, String payload) {
        this.type = type;
        this.title = title;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
