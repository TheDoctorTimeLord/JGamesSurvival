package com.jgames.survival.model.game.logic.attributes.rules;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.*;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import java.util.List;

public class MoveDistanceAttributeHandler implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(Features.CAN_MOVE, List.of(Features.FEATURES)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.LEFT_LEG)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.RIGHT_LEG)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        IntAttribute moveDistance =  battleModel.getAttributes().
                getAsContainer(Attributes.ATTRIBUTES).getAsInt(Attributes.MOVE_DISTANCE);
        AttributeMarker canMove = battleModel.getAttributes().getAsContainer(Features.FEATURES)
                .get(Features.CAN_MOVE);
        if (canMove != null && battleAttribute instanceof AttributesBasedAttribute leg) {
            String legState = leg.getAsString(StateConstants.STATE).getValue();
            moveDistance.setValue(Math.min(StateConstants.damageStrength.get(legState),
                    StateConstants.damageStrength.get(getAnotherLegState(battleModel, legState))));
        }
        else {
            moveDistance.setValue(0);
        }
        return List.of(new PuttedProcessedAttribute(moveDistance));
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
