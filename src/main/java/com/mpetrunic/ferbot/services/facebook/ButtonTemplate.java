package com.mpetrunic.ferbot.services.facebook;

import com.google.api.client.util.Key;

import java.util.ArrayList;
import java.util.List;

public class ButtonTemplate extends Template {

    @Key
    private String text;

    @Key
    private List<Button> buttons = new ArrayList<>();

    public ButtonTemplate(String text, List<Button> buttons) {
        super("button");
        this.text = text;
        this.buttons = buttons;
    }

    public ButtonTemplate(String text) {
        this(text, new ArrayList<>());
    }

    public String getText() {
        return text;
    }

    public ButtonTemplate setText(String text) {
        this.text = text;
        return this;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public ButtonTemplate addButton(Button button) {
        this.buttons.add(button);
        return this;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public FacebookMessengerRequest build(String recipient) {
        Message message = new Message();
        message.setAttachment(new Attachment(this));
        return new OutgoingFacebookMessage(recipient, message);
    }
}
