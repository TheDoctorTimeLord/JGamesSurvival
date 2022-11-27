package com.jgames.survival.viewmodel.core;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

@Bean
public class UpdatingSystemsManager {
    private final List<UpdatableOnGameTick> updatableElements;
    private final Logger logger;

    public UpdatingSystemsManager(List<UpdatableOnGameTick> updatableElements, Logger logger) {
        this.updatableElements = updatableElements;
        this.logger = logger;
    }

    public void updateElements() {
        for (UpdatableOnGameTick updatableElement : updatableElements) {
            try {
                updatableElement.update();
            } catch (Exception e) {
                logger.error("UpdatingSystemsManager", "Error when update element [%s]".formatted(updatableElement), e);
            }
        }
    }
}
