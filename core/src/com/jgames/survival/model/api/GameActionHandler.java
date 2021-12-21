package com.jgames.survival.model.api;

import com.jgames.survival.model.game.logic.GameBattleHandler;

public interface GameActionHandler<A extends GameAction> {
    void configure(GameBattleHandler battleHandler);
    Class<?> getHandlingActionClass();
    void handle(A action);
}
