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
 * Правило, по которому изменяется атрибут meleeDamagePoints некоторой модели на поле боя
 */
public class MeleeDamagedPointsAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.LEFT_ARM)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.RIGHT_ARM)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributesBasedAttribute arm) {
            String armState = arm.getAsString(StateConstants.STATE).getValue();
            int armDamageStrength = StateConstants.damageStrength.get(armState);
            int anotherArmDamageStrength = getAnotherArmDamageStrength(battleModel, armState);
            IntAttribute meleeDamagedPoints = battleModel.getAttributes().getAsContainer(Attributes.ATTRIBUTES)
                    .getAsInt(Attributes.MELEE_DAMAGE_POINTS);
            meleeDamagedPoints.setValue(armDamageStrength + anotherArmDamageStrength);
            return List.of(new PuttedProcessedAttribute(meleeDamagedPoints));
        }
        return List.of();
    }

    /**
     * Отвечает за получение состояния другой руки персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentArmState состояние одной из рук персонажа
     * @return целое число, характеризующие состояние другой руки персонажа
     */
    private int getAnotherArmDamageStrength(BattleModel battleModel, String currentArmState) {
        String leftArmState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.LEFT_ARM)
                .getAsString(StateConstants.STATE).getValue();
        String rightArmState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                .getAsContainer(BodyPartsConstants.RIGHT_ARM)
                .getAsString(StateConstants.STATE).getValue();
        if (currentArmState.equals(leftArmState))
            return StateConstants.damageStrength.get(rightArmState);
        return StateConstants.damageStrength.get(leftArmState);
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel,
                                                                    BattleAttribute battleAttribute) {
        return List.of();
    }
}
