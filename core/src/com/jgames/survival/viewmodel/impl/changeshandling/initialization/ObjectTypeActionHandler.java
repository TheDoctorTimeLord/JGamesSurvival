package com.jgames.survival.viewmodel.impl.changeshandling.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.game.logic.battle.initialization.ObjectTypeAction;

@Bean
public class ObjectTypeActionHandler extends GameObjectBasedActionHandler<ObjectTypeAction> {
    @Override
    public boolean canHandle(BattleAction battleAction) {
        return battleAction instanceof ObjectTypeAction;
    }

    @Override
    public Runnable createApplyingTask(ObjectTypeAction battleAction) {
        return () -> gameObjectsMutator.setObjectType(battleAction.getModelId(), battleAction.getModelTypeName());
    }
}
