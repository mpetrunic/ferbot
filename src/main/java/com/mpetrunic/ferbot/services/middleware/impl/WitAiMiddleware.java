package com.mpetrunic.ferbot.services.middleware.impl;

import com.mpetrunic.ferbot.services.drivers.impl.facebook.Entity;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.Extras;
import com.mpetrunic.ferbot.services.drivers.impl.facebook.IncomingFacebookMessage;
import com.mpetrunic.ferbot.services.middleware.IMiddleware;
import com.mpetrunic.ferbot.services.nlp.wit.WitAiService;
import com.mpetrunic.ferbot.services.nlp.wit.WitEntity;
import com.mpetrunic.ferbot.services.nlp.wit.WitResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WitAiMiddleware implements IMiddleware {

    private static final Logger LOGGER = LoggerFactory.getLogger(WitAiMiddleware.class);

    private WitAiService witAiService;

    private Double minConfidence;

    @Autowired
    public WitAiMiddleware(
            @Value("${ferbot.wit.minConfidence}") Double minConfidence,
            WitAiService witAiService
    ) {
        this.minConfidence = minConfidence;
        this.witAiService = witAiService;
    }

    @Override
    public IncomingFacebookMessage next(IncomingFacebookMessage message) {
        try {
            if (message.getText() == null || message.getText().isEmpty()) {
                return message;
            }
            WitResponse response = witAiService.processInput(message.getText());
            LOGGER.info("WIt ai -> {}", response.toString());
            List<WitEntity> intents = response.getEntities("intent");
            message.setExtras(new Extras());
            if (intents.size() > 0) {
                message.getExtras().setIntent(intents.get(0).getValue());
            }
            Map<String, Entity> entities = response.getEntities()
                    .entrySet()
                    .stream()
                    .filter(entry -> !Objects.equals(entry.getKey(), "intent") && entry.getValue().size() > 0)
                    .collect(Collectors.toMap(Map.Entry::getKey, o -> {
                        WitEntity wEntity = o.getValue().stream().sorted(Comparator.comparing(WitEntity::getConfidence)).findFirst().orElse(null);
                        if (wEntity != null && wEntity.getConfidence() >= minConfidence) {
                            return new Entity(wEntity.getType(), wEntity.getValue());
                        }
                        return null;
                    }));
            message.getExtras().setEntities(entities);
        } catch (IOException e) {
            LOGGER.error("Failed to obtain wit ai response", e);
        }
        return message;
    }
}
