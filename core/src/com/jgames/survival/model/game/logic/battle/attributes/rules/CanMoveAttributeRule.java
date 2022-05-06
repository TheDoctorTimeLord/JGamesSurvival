package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.LEFT_LEG;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.RIGHT_LEG;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue.DESTROYED;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features;

/**
 * Правило, по которому изменяется атрибут canMove некоторой модели на поле боя
 */
@BattleBeanPrototype
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
        if (battleAttribute instanceof IntAttribute state) {
            int legState = state.getValue();
            int anotherLegState = getAnotherLegState(battleModel, legState);
            if (DESTROYED.isLessOrEquals(legState) && DESTROYED.isLessOrEquals(anotherLegState))
            {
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
    private static int getAnotherLegState(BattleModel battleModel, int currentLegState) {
        int leftLegState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(LEFT_LEG)
                .getAsInt(STATE).getValue();
        int rightLegState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(RIGHT_LEG)
                .getAsInt(STATE).getValue();

        return currentLegState == leftLegState ? rightLegState : leftLegState;
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        return List.of();
    }
}
