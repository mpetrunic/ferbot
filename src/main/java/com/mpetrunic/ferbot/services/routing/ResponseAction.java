package com.mpetrunic.ferbot.services.routing;

@FunctionalInterface
public interface ResponseAction<IChatDriver, IncomingFacebookMessage> {

    void apply(IChatDriver driver, IncomingFacebookMessage message);

}
