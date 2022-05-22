package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.LEFT_ARM;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.RIGHT_ARM;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.ChangedAttributesContext;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;

/**
 * Правило, по которому изменяется атрибут meleeDamagePoints некоторой модели на поле боя
 */
@BattleBeanPrototype
public class MeleeDamagedPointsAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, LEFT_ARM)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, RIGHT_ARM)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof IntAttribute state) {
            BattleModel battleModel = context.getModel();
            int armDamageStrength = state.getValue();
            int anotherArmDamageStrength = getAnotherArmDamageStrength(battleModel, state);

            IntAttribute meleeDamagedPoints = battleModel.getAttributes().getAsContainer(Attributes.ATTRIBUTES)
                    .getAsInt(Attributes.MELEE_DAMAGE_POINTS)
                    .setValue(armDamageStrength + anotherArmDamageStrength);

            return List.of(new PuttedProcessedAttribute(meleeDamagedPoints));
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(ChangedAttributesContext context) {
        return List.of();
    }

    /**
     * Отвечает за получение состояния другой руки персонажа
     * @param battleModel объект, представляющий персонажа в бою
     * @param currentArmState состояние одной из рук персонажа
     * @return целое число, характеризующие состояние другой руки персонажа
     */
    private static int getAnotherArmDamageStrength(BattleModel battleModel, IntAttribute currentArmState) {
        IntAttribute leftArmState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(LEFT_ARM)
                .getAsInt(STATE);
        IntAttribute rightArmState = battleModel.getAttributes().getAsContainer(BodyParts.BODY_PARTS)
                .getAsContainer(RIGHT_ARM)
                .getAsInt(STATE);
        return currentArmState.equals(leftArmState) ? rightArmState.getValue() : leftArmState.getValue();
    }
}
