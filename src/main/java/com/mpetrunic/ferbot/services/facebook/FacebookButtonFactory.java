package com.mpetrunic.ferbot.services.facebook;

public class FacebookButtonFactory {

    public static Button postback(String title) {
        return postback(title, title);
    }

    public static Button postback(String title, String payload) {
        return new Button("postback", title, title);
    }
}
