package com.jgames.survival.model.game.logic.battle.events;

import com.jgames.survival.model.game.logic.battle.commands.MeleeAttackNotification;
import ru.jengine.battlemodule.core.battlepresenter.BattleActionRegistrar;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;

/**
 * Отправляет на регистрацию событие MeleeAttackEvent
 */
public class MeleeAttackEventNotifier implements PostHandler<MeleeAttackEvent> {
    private final BattleActionRegistrar actionRegistrar;

    public MeleeAttackEventNotifier(BattleActionRegistrar actionRegistrar) {
        this.actionRegistrar = actionRegistrar;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.RESULT_INFORMATION.getPriority();
    }

    @Override
    public Class<MeleeAttackEvent> getHandlingEventType() {
        return MeleeAttackEvent.class;
    }

    @Override
    public void handle(MeleeAttackEvent event) {
        MeleeAttackNotification notification = new MeleeAttackNotification(event);
        actionRegistrar.registerAction(notification);
    }
}
