package com.jgames.survival.model.game.logic.battle.events;

import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.utils.attributes.AttributeFindingUtils;

/**
 * Обрабатывает событие BodyPartDamageEvent
 */
public class BodyPartDamageEventHandler implements PostHandler<BodyPartDamageEvent> {
    private final BattleState battleState;
    private final DispatcherBattleWrapper dispatcher;

    public BodyPartDamageEventHandler(BattleState battleState, DispatcherBattleWrapper dispatcher)
    {
        this.battleState = battleState;
        this.dispatcher = dispatcher;
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }

    @Override
    public Class<BodyPartDamageEvent> getHandlingEventType() {
        return BodyPartDamageEvent.class;
    }

    @Override
    public void handle(BodyPartDamageEvent event) {
        BattleModel targetModel = battleState.resolveId(event.getTargetId());
        IntAttribute damagedBodyPartStateAttribute =
                AttributeFindingUtils.getBodyPartStateAttribute(targetModel, event.getDamagedBodyPart());
        if (damagedBodyPartStateAttribute == null) {
            return;
        }

        int bodyPartState = damagedBodyPartStateAttribute.getValue();
        int newBodyPartState = bodyPartState - 1;
        damagedBodyPartStateAttribute.setValue(newBodyPartState);

        AttributeUtils.notifyAboutChange(targetModel.getId(), dispatcher, damagedBodyPartStateAttribute);
    }
}
