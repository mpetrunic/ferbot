package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class QuickReply {

    @Key("content_type")
    private String contentType;

    @Key
    private String title;

    @Key
    private String payload;

    @Key("image_url")
    private String imageUrl;

    public QuickReply(String contentType) {
        this(contentType, null, null, null);
    }

    public QuickReply(String title, String payload) {
        this.contentType = "text";
        this.title = title;
        this.payload = payload;
    }

    public QuickReply(String contentType, String title, String payload, String imageUrl) {
        this.contentType = contentType;
        this.title = title;
        this.payload = payload;
        this.imageUrl = imageUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public String getTitle() {
        return title;
    }

    public String getPayload() {
        return payload;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
