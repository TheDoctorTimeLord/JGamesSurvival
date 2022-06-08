package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.events.BattleEvent;
import ru.jengine.eventqueue.event.PostHandler;

import com.jgames.survival.utils.ReflectionUtils;
import com.jgames.survival.utils.ReflectionUtils.LazyGenericTypesIterable;

public abstract class BaseBattlePostHandler<E extends BattleEvent> implements PostHandler<E> {
    @Override
    @SuppressWarnings("unchecked")
    public Class<E> getHandlingEventType() {
        return (Class<E>)ReflectionUtils.getGenericInterfaceType(
                new LazyGenericTypesIterable(getClass()),
                BaseBattlePostHandler.class,
                0
        );
    }
}
