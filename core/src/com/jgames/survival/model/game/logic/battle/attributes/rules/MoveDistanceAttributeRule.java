package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.LEFT_LEG;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.RIGHT_LEG;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.CAN_MOVE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.FEATURES;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;

/**
 * Правило, по которому изменяется атрибут moveDistance некоторой модели на поле боя
 */
@BattleBeanPrototype
public class MoveDistanceAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(CAN_MOVE, List.of(FEATURES)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, LEFT_LEG)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, RIGHT_LEG)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        IntAttribute moveDistance =  battleModel.getAttributes()
                .getAsContainer(Attributes.ATTRIBUTES)
                .getAsInt(Attributes.MOVE_DISTANCE);

        if (battleAttribute instanceof IntAttribute state) {
            int legState = state.getValue();
            int anotherLegState = getAnotherLegState(battleModel, state);

            moveDistance.setValue(Math.min(legState, anotherLegState));

            return List.of(new PuttedProcessedAttribute(moveDistance));
        }

        return List.of();
    }

    /**
     * Отвечает за получение состояния другой ноги персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentLegState состояние одной из ног персонажа
     * @return состояние другой ноги персонажа
     */
    private static int getAnotherLegState(BattleModel battleModel, IntAttribute currentLegState) {
        IntAttribute leftLegState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(LEFT_LEG)
                .getAsInt(STATE);
        IntAttribute rightLegState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(RIGHT_LEG)
                .getAsInt(STATE);
        return currentLegState.equals(leftLegState) ? rightLegState.getValue() : leftLegState.getValue();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        IntAttribute moveDistance =  battleModel.getAttributes()
                .getAsContainer(Attributes.ATTRIBUTES)
                .getAsInt(Attributes.MOVE_DISTANCE);

        if (battleAttribute instanceof AttributeMarker canMove && CAN_MOVE.equals(canMove.getCode())) {
            return List.of(new PuttedProcessedAttribute(moveDistance.setValue(0)));
        }

        return List.of();
    }
}
