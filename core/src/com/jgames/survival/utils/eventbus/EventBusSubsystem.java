package com.jgames.survival.utils.eventbus;

import java.util.Collection;

public interface EventBusSubsystem {
    /**
     * Возвращает слушатели шины событий. Метод обязан возвращать каждый раз один и тот же набор объектов слушателей,
     * иначе поведение системы будет непредсказуемо.
     */
    Collection<? extends EventBusSubsystemPostHandler<? extends BusEvent>> getRegisteringListeners();
}
