package com.jgames.survival.model;

import com.jgames.survival.model.api.GameAction;

public interface GameActionSender {
    void sendGameAction(GameAction action);
}
