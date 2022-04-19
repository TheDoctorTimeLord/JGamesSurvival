package com.jgames.survival.model.game.logic.battle.events;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.fighter.Fighter;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.scheduler.SchedulerTaskRegistrar;
import ru.jengine.battlemodule.core.state.BattleDynamicObjectsManager;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Обрабатывает событие MeleeAttackEvent
 */
public class MeleeAttackEventHandler implements PostHandler<MeleeAttackEvent> {
    private final BattleState battleState;
    private final SchedulerTaskRegistrar taskRegistrar;
    private final BattleDynamicObjectsManager dynamicObjectsManager;

    public MeleeAttackEventHandler(BattleState battleState, SchedulerTaskRegistrar taskRegistrar,
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
    public Class<MeleeAttackEvent> getHandlingEventType() {
        return MeleeAttackEvent.class;
    }

    @Override
    public void handle(MeleeAttackEvent event) {
        BattleModel model = battleState.resolveId(event.getTarget());
        if (setNewHealthValue(model, event)) {
            setNewBodyPartState(model, event);
        }
    }

    /**
     * Назначение нового значения здоровья персонажа
     * @param model объект, представляющий персонажа в бою
     * @param event событие ближнего боя
     * @return true, если персонаж остался жив, false - иначе
     */
    private boolean setNewHealthValue(BattleModel model, MeleeAttackEvent event) {
        boolean isAlive = true;
        if (model instanceof Fighter fighter) {
            fighter.damage(event.getDamagePoints());
            if (fighter.isDead()) {
                taskRegistrar.addTaskAfterTurn(() -> dynamicObjectsManager.removeCharacter(model.getId()));
                isAlive = false;
            }
        }
        return isAlive;
    }

    /**
     * Назначение нового состояния части тела персонажа
     * @param model объект, представляющий персонажа в бою
     * @param event событие ближнего боя
     */
    private void setNewBodyPartState(BattleModel model, MeleeAttackEvent event) {
        String bodyPartState = model.getAttributes().getAsString(event.getDamagedBodyPart()).getValue();
        int newIntBodyPartState = StateConstants.damageStrength.get(bodyPartState) - 1;
        String newBodyPartState = getNewBodyPartState(newIntBodyPartState);
        model.getAttributes().getAsString(event.getDamagedBodyPart()).setValue(newBodyPartState);
    }

    /**
     * Отвечает за получение нового состояния части тела
     * @param newIntBodyPartState целое число, характеризующее новое состояние части тела персонажа
     * @return строковое значение, характеризующие новое состояние части тела персонажа
     */
    private String getNewBodyPartState(int newIntBodyPartState) {
        if (newIntBodyPartState <= 0)
            return StateConstants.DESTROYED;
        if (newIntBodyPartState == 1)
            return StateConstants.DAMAGED;
        return StateConstants.GOOD;
    }
}
