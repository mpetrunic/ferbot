package com.mpetrunic.ferbot.services.nlp.wit;

import com.google.api.client.util.Key;

public class WitEntity {

    @Key("type")
    private String type;

    @Key("value")
    private String value;

    @Key("confidence")
    private Double confidence;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}
