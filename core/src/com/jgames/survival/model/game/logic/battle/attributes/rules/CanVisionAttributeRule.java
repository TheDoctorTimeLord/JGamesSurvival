package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.HEAD;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.ChangedAttributesContext;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue;

/**
 * Правило, по которому изменяется атрибут canVision у некоторой модели на поле боя
 */
@BattleBeanPrototype
public class CanVisionAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, HEAD)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof IntAttribute state) {
            int headState = state.getValue();
            if (StateValue.DESTROYED.isLessOrEquals(headState)) {
                BattleAttribute canVision = context.getModel().getAttributes().
                        getAsContainer(Features.FEATURES)
                        .remove(Features.CAN_VISION);
                return List.of(new RemovedProcessedAttribute(canVision));
            }
        }
        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(ChangedAttributesContext context) {
        return List.of();
    }
}
