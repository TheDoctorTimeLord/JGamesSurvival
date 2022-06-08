package com.jgames.survival.model.game.logic.battle.events.deadmodel;

import ru.jengine.battlemodule.core.scheduler.SchedulerTaskRegistrar;
import ru.jengine.battlemodule.core.state.BattleDynamicObjectsManager;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;

import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;

public class DeadDynamicModelHandler extends BaseBattlePostHandler<DeadDynamicModelEvent> {
    private final SchedulerTaskRegistrar taskRegistrar;
    private final BattleDynamicObjectsManager dynamicObjectsManager;

    public DeadDynamicModelHandler(SchedulerTaskRegistrar taskRegistrar,
            BattleDynamicObjectsManager dynamicObjectsManager) {
        this.taskRegistrar = taskRegistrar;
        this.dynamicObjectsManager = dynamicObjectsManager;
    }

    @Override
    public void handle(DeadDynamicModelEvent event) {
        taskRegistrar.addTaskAfterTurn(() -> dynamicObjectsManager.removeCharacter(event.getModelId()));
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }
}
