package com.jgames.survival.model.api;

public interface GameActionHandler<A extends GameAction> {
    Class<?> getHandlingActionClass();
    void handle(A action);
}
