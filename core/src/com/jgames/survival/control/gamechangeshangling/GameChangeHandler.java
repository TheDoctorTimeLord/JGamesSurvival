package com.jgames.survival.control.gamechangeshangling;

import com.jgames.survival.model.api.GameChangeListener;

public interface GameChangeHandler extends GameChangeListener {
    void setGameState(PresentingGameState gameState);
}
