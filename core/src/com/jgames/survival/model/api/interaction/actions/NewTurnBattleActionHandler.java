package com.jgames.survival.model.api.interaction.actions;

import ru.jengine.beancontainer.annotations.Bean;

@Bean
public class NewTurnBattleActionHandler extends BaseActionHandler<NewTurnBattleAction> {
    @Override
    public void handle(NewTurnBattleAction action) {
        gameBattleHandler.getBattleMaster().takeTurn();
    }
}
