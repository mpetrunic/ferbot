package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class Recipient {

    @Key
    private String id;

    public Recipient(String recipient) {
        this.id = recipient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
