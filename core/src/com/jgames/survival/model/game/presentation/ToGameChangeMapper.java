package com.jgames.survival.model.game.presentation;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.game.logic.GameBattleHandler;

public interface ToGameChangeMapper {
    void configureMapper(GameChangeSender changeSender, GameBattleHandler battleHandler);
}
