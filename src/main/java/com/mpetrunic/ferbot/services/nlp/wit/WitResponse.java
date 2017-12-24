package com.mpetrunic.ferbot.services.nlp.wit;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class WitResponse {

    @Key("msg_id")
    private String msgId;

    @Key("_text")
    private String text;

    @Key
    private Map<String, List<WitEntity>> entities;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, List<WitEntity>> getEntities() {
        return entities;
    }

    public void setEntities(Map<String, List<WitEntity>> entities) {
        this.entities = entities;
    }

    public List<WitEntity> getEntities(String key) {
        List<WitEntity> results = getEntities().getOrDefault(key, new ArrayList<>());
        results.sort(Comparator.comparing(WitEntity::getConfidence));
        return results;
    }

    @Override
    public String toString() {
        return "WitResponse{" +
                "msgId='" + msgId + '\'' +
                ", text='" + text + '\'' +
                ", entities=" + entities +
                '}';
    }
}
