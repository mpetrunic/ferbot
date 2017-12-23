package com.mpetrunic.ferbot.services.drivers.impl.facebook;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private String type;

    private String value;

    private Map<String, Object> params = new HashMap<>();

    public Entity(String type, String value) {
        this(type, value, new HashMap<>());
    }

    public Entity(String type, String value, Map<String, Object> params) {
        this.type = type;
        this.value = value;
        this.params = params;
    }

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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
