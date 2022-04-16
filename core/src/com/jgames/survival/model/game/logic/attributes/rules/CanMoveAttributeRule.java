package com.jgames.survival.model.game.logic.attributes.rules;

import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.BODY_PARTS;
import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.LEFT_LEG;
import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.RIGHT_LEG;
import static com.jgames.survival.model.game.logic.attributes.constants.StateConstants.DESTROYED;
import static com.jgames.survival.model.game.logic.attributes.constants.StateConstants.STATE;

import java.util.List;

import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants;
import com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.Features;

/**
 * Правило, по которому изменяется атрибут canMove некоторой модели на поле боя
 */
public class CanMoveAttributeRule implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, LEFT_LEG)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, RIGHT_LEG))
        );
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof StringAttribute state) {
            String legState = state.getValue();
            if (DESTROYED.equals(legState) && DESTROYED.equals(getAnotherLegState(battleModel, legState))) {
                BattleAttribute canMove =  battleModel.getAttributes()
                        .getAsContainer(Features.FEATURES)
                        .remove(Features.CAN_MOVE);
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
                .getAsContainer(LEFT_LEG)
                .getAsString(STATE).getValue();
        String rightLegState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(RIGHT_LEG)
                .getAsString(STATE).getValue();
        if (currentLegState.equals(leftLegState))
            return rightLegState;
        return leftLegState;
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        return List.of();
    }
}
