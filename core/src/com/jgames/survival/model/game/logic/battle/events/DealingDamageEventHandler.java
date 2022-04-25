package com.jgames.survival.model.game.logic.battle.events;

import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.scheduler.SchedulerTaskRegistrar;
import ru.jengine.battlemodule.core.state.BattleDynamicObjectsManager;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Обрабатывает событие DealingDamageEvent
 */
public class DealingDamageEventHandler implements PostHandler<DealingDamageEvent> {
    private final BattleState battleState;
    private final SchedulerTaskRegistrar taskRegistrar;
    private final BattleDynamicObjectsManager dynamicObjectsManager;

    public DealingDamageEventHandler(BattleState battleState, SchedulerTaskRegistrar taskRegistrar,
                                     BattleDynamicObjectsManager dynamicObjectsManager)
    {
        this.battleState = battleState;
        this.taskRegistrar = taskRegistrar;
        this.dynamicObjectsManager = dynamicObjectsManager;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }

    @Override
    public Class<DealingDamageEvent> getHandlingEventType() {
        return DealingDamageEvent.class;
    }

    @Override
    public void handle(DealingDamageEvent event) {
        BattleModel targetModel = battleState.resolveId(event.getTargetId());
        setNewHealthValue(targetModel, event);
    }

    /**
     * Назначение нового значения здоровья персонажа
     * @param model объект, представляющий персонажа в бою
     * @param event событие ближнего боя
     */
    private void setNewHealthValue(BattleModel model, DealingDamageEvent event) {
        if (model instanceof Fighter fighter) {
            fighter.damage(event.getDamagePoints());
            if (fighter.isDead()) {
                taskRegistrar.addTaskAfterTurn(() -> dynamicObjectsManager.removeCharacter(model.getId()));
            }
        }
    }
}
