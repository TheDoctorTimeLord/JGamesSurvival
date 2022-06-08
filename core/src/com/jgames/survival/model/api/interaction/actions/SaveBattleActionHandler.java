package com.jgames.survival.model.api.interaction.actions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.events.savebattle.SaveBattleEvent;

@Bean
public class SaveBattleActionHandler extends BaseActionHandler<SaveBattleAction> {
    @Override
    public void handle(SaveBattleAction action) throws IOException {
        OutputStream stream = new FileOutputStream(action.getSaveTarget());
        gameBattleHandler.getBattleMaster().handleBattleEvent(new SaveBattleEvent(stream));
    }
}
