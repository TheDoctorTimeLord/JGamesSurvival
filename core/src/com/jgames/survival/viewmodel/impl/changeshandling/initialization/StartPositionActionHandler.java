package com.jgames.survival.viewmodel.impl.changeshandling.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.initialization.StartPositionAction;

@Bean
public class StartPositionActionHandler extends GameObjectBasedActionHandler<StartPositionAction> {
    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof StartPositionAction;
    }

    @Override
    public Runnable createApplyingTask(StartPositionAction battleAction) {
        return () -> gameObjectsMutator.setPositionData(
                battleAction.getModelId(),
                battleAction.getModelPosition(),
                battleAction.getModelDirection()
        );
    }
}
