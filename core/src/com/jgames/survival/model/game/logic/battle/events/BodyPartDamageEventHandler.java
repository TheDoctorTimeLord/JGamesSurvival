package com.jgames.survival.model.game.logic.battle.events;

import com.jgames.survival.model.game.logic.attributes.constants.StateConstants;
import com.jgames.survival.model.game.logic.attributes.utils.GetAttributeUtils;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.eventqueue.event.PostHandler;
import ru.jengine.utils.AttributeUtils;

/**
 * Обрабатывает событие BodyPartDamageEvent
 */
public class BodyPartDamageEventHandler implements PostHandler<BodyPartDamageEvent> {
    private final BattleState battleState;
    DispatcherBattleWrapper dispatcher;

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
        setNewBodyPartState(targetModel, event);
    }

    /**
     * Назначение нового состояния части тела персонажа
     * @param model объект, представляющий персонажа в бою
     * @param event событие ближнего боя
     */
    private void setNewBodyPartState(BattleModel model, BodyPartDamageEvent event) {
        StringAttribute damagedBodyPartStateAttribute = GetAttributeUtils
                .getBodyPartStateAttribute(model, event.getDamagedBodyPart());
        String bodyPartState = damagedBodyPartStateAttribute.getValue();
        int newIntBodyPartState = StateConstants.damageStrength.get(bodyPartState) - 1;
        String newBodyPartState = getNewBodyPartState(newIntBodyPartState);
        damagedBodyPartStateAttribute.setValue(newBodyPartState);
        AttributeUtils.notifyAboutChange(model.getId(), dispatcher, damagedBodyPartStateAttribute);
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
