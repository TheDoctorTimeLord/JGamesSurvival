package com.jgames.survival.model.game.logic.attributes.rules;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.*;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import java.util.List;

/**
 * Правило, по которому изменяется атрибут hitPoints некоторой модели на поле боя
 */
public class HitPointsAttributeRule implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.HEAD)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.BODY)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributesBasedAttribute bodyPart) {
            String bodyPartState = bodyPart.getAsString(StateConstants.STATE).getValue();
            if (StateConstants.DESTROYED.equals(bodyPartState) ||
                    StateConstants.DESTROYED.equals(getAnotherBodyPartDamageStrength(battleModel, bodyPartState))) {
                IntAttribute hitPoints =  battleModel.getAttributes().
                        getAsContainer(Attributes.ATTRIBUTES).getAsInt(Attributes.HIT_POINTS);
                hitPoints.setValue(0);
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
    private String getAnotherBodyPartDamageStrength(BattleModel battleModel, String currentBodyPartState) {
        String headState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.HEAD)
                .getAsString(StateConstants.STATE).getValue();
        String bodyState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.BODY)
                .getAsString(StateConstants.STATE).getValue();
        if (currentBodyPartState.equals(headState))
            return bodyState;
        return headState;
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel,
                                                                    BattleAttribute battleAttribute) {
        return List.of();
    }
}
