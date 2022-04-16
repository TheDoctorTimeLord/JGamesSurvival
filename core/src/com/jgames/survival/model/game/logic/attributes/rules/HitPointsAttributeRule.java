package com.jgames.survival.model.game.logic.attributes.rules;

import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.BODY;
import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.BODY_PARTS;
import static com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants.HEAD;
import static com.jgames.survival.model.game.logic.attributes.constants.StateConstants.DESTROYED;
import static com.jgames.survival.model.game.logic.attributes.constants.StateConstants.STATE;

import java.util.List;

import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants.BodyPartsConstants;

/**
 * Правило, по которому изменяется атрибут hitPoints некоторой модели на поле боя
 */
public class HitPointsAttributeRule implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, HEAD)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, BODY)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof StringAttribute state) {
            String bodyPartState = state.getValue();
            if (DESTROYED.equals(bodyPartState) ||
                    DESTROYED.equals(getAnotherBodyPartDamageStrength(battleModel, bodyPartState)))
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

    /**
     * Отвечает за получение состояния другой части тела персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentBodyPartState состояние одной из части тела персонажа
     * @return состояние другой части тела персонажа
     */
    private static String getAnotherBodyPartDamageStrength(BattleModel battleModel, String currentBodyPartState) {
        String headState = battleModel.getAttributes()
                .getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(HEAD)
                .getAsString(STATE).getValue();
        String bodyState = battleModel.getAttributes()
                .getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BODY)
                .getAsString(STATE).getValue();
        if (currentBodyPartState.equals(headState))
            return bodyState;
        return headState;
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        return List.of();
    }
}
