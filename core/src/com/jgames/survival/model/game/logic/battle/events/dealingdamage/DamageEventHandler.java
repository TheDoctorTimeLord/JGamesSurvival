package com.jgames.survival.model.game.logic.battle.events.dealingdamage;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.scheduler.SchedulerTaskRegistrar;
import ru.jengine.battlemodule.core.state.BattleDynamicObjectsManager;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

import com.jgames.survival.model.game.logic.battle.models.HasHealth;

/**
 * Обрабатывает событие DealingDamageEvent
 */
public class DamageEventHandler implements PostHandler<DamageEvent> {
    private final BattleState battleState;
    private final SchedulerTaskRegistrar taskRegistrar;
    private final BattleDynamicObjectsManager dynamicObjectsManager;
    private final DispatcherBattleWrapper dispatcher;

    public DamageEventHandler(BattleState battleState, SchedulerTaskRegistrar taskRegistrar,
            BattleDynamicObjectsManager dynamicObjectsManager, DispatcherBattleWrapper dispatcher)
    {
        this.battleState = battleState;
        this.taskRegistrar = taskRegistrar;
        this.dynamicObjectsManager = dynamicObjectsManager;
        this.dispatcher = dispatcher;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }

    @Override
    public Class<DamageEvent> getHandlingEventType() {
        return DamageEvent.class;
    }

    @Override
    public void handle(DamageEvent event) {
        BattleModel targetModel = battleState.resolveId(event.getTargetId());
        if (targetModel instanceof HasHealth hasHealth) {
            hasHealth.damage(event.getDamagePoints(), dispatcher);
            if (hasHealth.isDead()) {
                taskRegistrar.addTaskAfterTurn(() -> dynamicObjectsManager.removeCharacter(targetModel.getId()));
            }
        }
    }
}
