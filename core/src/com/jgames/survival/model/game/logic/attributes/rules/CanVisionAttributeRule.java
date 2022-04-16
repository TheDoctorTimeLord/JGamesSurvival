package com.jgames.survival.model.game.logic.attributes.rules;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.*;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import java.util.List;

/**
 * Правило, по которому изменяется атрибут canVision у некоторой модели на поле боя
 */
public class CanVisionAttributeRule implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(BodyPartsConstants.HEAD,
                List.of(BodyPartsConstants.BODY_PARTS)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributesBasedAttribute head) {
            String headState = head.getAsString(StateConstants.STATE).getValue();
            if (StateConstants.DESTROYED.equals(headState)) {
                AttributeMarker canVision =  battleModel.getAttributes().
                        getAsContainer(Features.FEATURES).get(Features.CAN_VISION);
                battleModel.getAttributes().getAsContainer(Features.FEATURES).remove(Features.CAN_VISION);
                return List.of(new RemovedProcessedAttribute(canVision));
            }
        }
        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel,
                                                                    BattleAttribute battleAttribute) {
        return List.of();
    }
}
