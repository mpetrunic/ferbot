package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

import java.util.List;

public class Message {

    @Key
    private String text;

    @Key
    private Attachment attachment;

    @Key("quick_replies")
    private List<QuickReply> quickReplies;

    public Message(String message) {
        text = message;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public void setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
    }
}
