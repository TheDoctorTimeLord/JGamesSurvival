package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.scheduler.SchedulerTaskRegistrar;
import ru.jengine.battlemodule.core.state.BattleDynamicObjectsManager;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

import com.jgames.survival.model.game.logic.battle.models.HasHealth;

public class RangedAttackHandler implements PostHandler<RangedAttackEvent> {
    private final BattleState battleState;
    private final DispatcherBattleWrapper dispatcher;
    private final SchedulerTaskRegistrar taskRegistrar;
    private final BattleDynamicObjectsManager dynamicObjectsManager;

    public RangedAttackHandler(BattleState battleState, DispatcherBattleWrapper dispatcher,
            SchedulerTaskRegistrar taskRegistrar, BattleDynamicObjectsManager dynamicObjectsManager)
    {
        this.battleState = battleState;
        this.dispatcher = dispatcher;
        this.taskRegistrar = taskRegistrar;
        this.dynamicObjectsManager = dynamicObjectsManager;
    }

    @Override
    public Class<RangedAttackEvent> getHandlingEventType() {
        return RangedAttackEvent.class;
    }

    @Override
    public void handle(RangedAttackEvent event) {
        BattleModel target = battleState.resolveId(event.getTargetId());
        if (!(target instanceof HasHealth hasHealth)) {
            return;
        }

        hasHealth.damage(event.getDamagePoints(), dispatcher);
        if (hasHealth.isDead()) {
            taskRegistrar.addTaskAfterTurn(() -> dynamicObjectsManager.removeCharacter(target.getId()));
        }
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }
}
