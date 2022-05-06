package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.HEAD;
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
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;

/**
 * Правило, по которому изменяется атрибут hitPoints некоторой модели на поле боя
 */
@BattleBeanPrototype
public class HitPointsAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, HEAD)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, BODY)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof IntAttribute state) {
            int bodyPartState = state.getValue();
            int anotherBodyPartState = getAnotherBodyPartDamageStrength(battleModel, bodyPartState);

            if (DESTROYED.isLessOrEquals(bodyPartState) || DESTROYED.isLessOrEquals(anotherBodyPartState))
            {
                IntAttribute hitPoints =  battleModel.getAttributes().
                        getAsContainer(Attributes.ATTRIBUTES)
                        .getAsInt(Attributes.HIT_POINTS)
                        .setValue(0);
                return List.of(new PuttedProcessedAttribute(hitPoints));
            }
        }
        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        return List.of();
    }

    /**
     * Отвечает за получение состояния другой части тела персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentBodyPartState состояние одной из части тела персонажа
     * @return состояние другой части тела персонажа
     */
    private static int getAnotherBodyPartDamageStrength(BattleModel battleModel, int currentBodyPartState) {
        int headState = battleModel.getAttributes()
                .getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(HEAD)
                .getAsInt(STATE).getValue();
        int bodyState = battleModel.getAttributes()
                .getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(BODY)
                .getAsInt(STATE).getValue();
        return currentBodyPartState == headState ? bodyState : headState;
    }
}
