package com.mpetrunic.ferbot.services.middleware;

import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;

public interface IMiddleware {

    IncomingFacebookMessage next(IncomingFacebookMessage message);

}
