package com.mpetrunic.ferbot.services.drivers.impl.facebook;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class IncomingFacebookMessage {

    private String sender;

    private String recipient;

    private LocalDateTime timestamp;

    private MessageType type;

    private String mid;

    private Integer seq;

    private String text;

    private Extras extras = new Extras();

    @SuppressWarnings("unchecked")
    public IncomingFacebookMessage(Map<String, Object> message) {
        type = MessageType.TEXT;
        this.sender = (String) ((Map<String, Object>)message.getOrDefault("sender", new HashMap<>())).getOrDefault("id", "");
        this.recipient = (String) ((Map<String, Object>)message.getOrDefault("recipient", new HashMap<>())).getOrDefault("id", "");
        if(message.containsKey("timestamp")) {
            this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli((Long) message.get("timestamp")),
                    TimeZone.getDefault().toZoneId());
        }
        if (message.containsKey("message")) {
            Map<String, Object> messageContent = (Map<String, Object>) message.getOrDefault("message", new HashMap<>());
            this.mid = (String) messageContent.getOrDefault("mid", "");
            this.seq = (Integer) messageContent.getOrDefault("seq", 0);
            this.text = (String) messageContent.getOrDefault("text", "");
        }
        if (message.containsKey("postback")) {
            Map<String, Object> messageContent = (Map<String, Object>) message.getOrDefault("postback", new HashMap<>());
            this.text = (String) messageContent.getOrDefault("payload", "");
        }
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomingFacebookMessage that = (IncomingFacebookMessage) o;

        return mid != null ? mid.equals(that.mid) : that.mid == null;
    }

    @Override
    public int hashCode() {
        return mid != null ? mid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IncomingFacebookMessage{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", mid='" + mid + '\'' +
                ", seq=" + seq +
                ", text='" + text + '\'' +
                ", extras=" + extras +
                '}';
    }
}
