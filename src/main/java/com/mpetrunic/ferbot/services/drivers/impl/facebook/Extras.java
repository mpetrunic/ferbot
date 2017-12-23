package com.mpetrunic.ferbot.services.drivers.impl.facebook;

import java.util.HashMap;
import java.util.Map;

public class Extras {

    private String intent;

    private Map<String, Entity> entities = new HashMap<>();

    public Extras() {
    }

    public Extras(String intent, Map<String, Entity> entities) {
        this.intent = intent;
        this.entities = entities;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Map<String, Entity> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Extras{" +
                "intent='" + intent + '\'' +
                ", entities=" + entities +
                '}';
    }
}
