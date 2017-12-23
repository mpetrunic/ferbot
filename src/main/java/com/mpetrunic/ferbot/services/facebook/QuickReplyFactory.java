package com.mpetrunic.ferbot.services.facebook;

public class QuickReplyFactory {

    public static QuickReply userLoactionButton() {
        return new QuickReply("location");
    }

    public static QuickReply button(String title, String payload) {
        return new QuickReply(title, payload);
    }

    public static QuickReply button(String title) {
        return new QuickReply(title, title);
    }

    public static QuickReply imageButton(String title, String payload, String imageUrl) {
        return new QuickReply("text", title, payload, imageUrl);
    }

    public static QuickReply imageButton(String title, String imageUrl) {
        return new QuickReply("text", title, title, imageUrl);
    }


}
