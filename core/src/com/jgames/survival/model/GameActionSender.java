package com.jgames.survival.model;

import com.jgames.survival.model.api.interaction.GameAction;

public interface GameActionSender {
    void sendGameAction(GameAction action);
}
