package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

public class Template {

    @Key("template_type")
    private String type;

    public Template(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Template{" +
                "type='" + type + '\'' +
                '}';
    }
}
