package com.jgames.survival.model.api.interaction.actions;

import com.jgames.survival.model.api.interaction.GameAction;
import com.jgames.survival.model.api.interaction.GameActionHandler;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.utils.ReflectionUtils;
import com.jgames.survival.utils.ReflectionUtils.LazyGenericTypesIterable;

public abstract class BaseActionHandler<A extends GameAction> implements GameActionHandler<A> {
    protected GameBattleHandler gameBattleHandler;

    @Override
    public void configure(GameBattleHandler battleHandler) {
        gameBattleHandler = battleHandler;
    }

    @Override
    public Class<?> getHandlingActionClass() {
        return ReflectionUtils.getGenericInterfaceType(new LazyGenericTypesIterable(getClass()), BaseActionHandler.class, 0);
    }
}
