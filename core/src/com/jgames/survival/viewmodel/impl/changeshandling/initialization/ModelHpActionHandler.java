package com.jgames.survival.viewmodel.impl.changeshandling.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.initialization.ModelHpAction;

@Bean
public class ModelHpActionHandler extends GameObjectBasedActionHandler<ModelHpAction> {
    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof ModelHpAction;
    }

    @Override
    public Runnable createApplyingTask(ModelHpAction battleAction) {
        return () -> gameObjectsMutator.setHp(battleAction.getModelId(), battleAction.getHp());
    }
}
