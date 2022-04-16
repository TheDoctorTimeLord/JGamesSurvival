package com.jgames.survival.model.game.logic.attributes.rules;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.*;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import java.util.List;

public class VisionDistanceAttributeHandler implements AttributeRule {

    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(Features.CAN_VISION, List.of(Features.FEATURES)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BodyPartsConstants.HEAD)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel,
                                                                   BattleAttribute battleAttribute) {
        StringAttribute visionDistance = battleModel.getAttributes().getAsContainer(Attributes.ATTRIBUTES)
                .get(Attributes.VISION_DISTANCE);
        if (battleAttribute instanceof AttributesBasedAttribute head) {
            String headState = head.getAsString(StateConstants.STATE).getValue();
            AttributeMarker canVision = battleModel.getAttributes().getAsContainer(Features.FEATURES)
                    .get(Features.CAN_VISION);
            changeVisionDistance(headState, canVision, visionDistance);
            return List.of(new PuttedProcessedAttribute(visionDistance));
        }
        else if (battleAttribute instanceof AttributeMarker canVision) {
            String headState = battleModel.getAttributes().getAsContainer(BodyPartsConstants.BODY_PARTS)
                    .getAsContainer(BodyPartsConstants.HEAD)
                    .getAsString(StateConstants.STATE).getValue();
            changeVisionDistance(headState, canVision, visionDistance);
            return List.of(new PuttedProcessedAttribute(visionDistance));
        }
        return List.of();
    }

    /**
     * Отвечает за изменение значения атрибута visionDistance в зависимости от значений атрибутов headState и canVision
     * @param headState состояние головы персонажа
     * @param canVision атрибут, который отвечает за то, может ли персонаж видеть
     * @param visionDistance атрибут, который отвечает за расстояние, на которое может видеть персонаж
     */
    private void changeVisionDistance(String headState, AttributeMarker canVision, StringAttribute visionDistance) {
        if (canVision == null) {
            visionDistance.setValue(StateConstants.NONE);
        }
        else {
            if (StateConstants.GOOD.equals(headState)) {
                visionDistance.setValue(StateConstants.FAR);
            }
            else if (StateConstants.DAMAGED.equals(headState)) {
                visionDistance.setValue(StateConstants.NEAR);
            }
            else if (StateConstants.DESTROYED.equals(headState)) {
                visionDistance.setValue(StateConstants.NONE);
            }
        }
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel,
                                                                    BattleAttribute battleAttribute) {
        return List.of();
    }
}
