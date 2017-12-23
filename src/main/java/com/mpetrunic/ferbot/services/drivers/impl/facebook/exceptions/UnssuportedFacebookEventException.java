package com.mpetrunic.ferbot.services.drivers.impl.facebook.exceptions;

import com.mpetrunic.ferbot.services.drivers.impl.facebook.RequestType;

public class UnssuportedFacebookEventException extends FacebookDriverException {
    public UnssuportedFacebookEventException(RequestType requestType) {
        super("Request type: "+requestType.name()+" isn't supported!");
    }
}
