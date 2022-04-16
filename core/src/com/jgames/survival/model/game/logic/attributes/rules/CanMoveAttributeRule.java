package com.jgames.survival.model.game.logic.attributes.rules;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.BodyPartsConstants;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.Features;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import java.util.List;

/**
 * Правило, по которому изменяется атрибут canMove некоторой модели на поле боя
 */
public class CanMoveAttributeRule implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.LEFT_LEG)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.RIGHT_LEG)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributesBasedAttribute leg) {
            String legState = leg.getAsString(StateConstants.STATE).getValue();
            if (StateConstants.DESTROYED.equals(legState) &&
                    StateConstants.DESTROYED.equals(getAnotherLegState(battleModel, legState))) {
                AttributeMarker canMove =  battleModel.getAttributes().
                        getAsContainer(Features.FEATURES).get(Features.CAN_MOVE);
                battleModel.getAttributes().getAsContainer(Features.FEATURES).remove(Features.CAN_MOVE);
                return List.of(new RemovedProcessedAttribute(canMove));
            }
        }
        return List.of();
    }

    /**
     * Отвечает за получение состояния другой ноги персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentLegState состояние одной из ног персонажа
     * @return состояние другой ноги персонажа
     */
    private String getAnotherLegState(BattleModel battleModel, String currentLegState) {
        String leftLegState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.LEFT_LEG)
                .getAsString(StateConstants.STATE).getValue();
        String rightLegState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.RIGHT_LEG)
                .getAsString(StateConstants.STATE).getValue();
        if (currentLegState.equals(leftLegState))
            return rightLegState;
        return leftLegState;
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel,
                                                                    BattleAttribute battleAttribute) {
        return List.of();
    }
}
