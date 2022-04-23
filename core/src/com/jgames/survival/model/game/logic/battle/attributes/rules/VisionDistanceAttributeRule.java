package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.ATTRIBUTES;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.VISION_DISTANCE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants.HEAD;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.CAN_VISION;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.FEATURES;

import java.util.List;

import ru.jengine.battlemodule.core.modelattributes.BattleAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateConstants;

/**
 * Правило, по которому изменяется атрибут visionDistance некоторой модели на поле боя
 */
public class VisionDistanceAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(CAN_VISION, List.of(FEATURES)),
                new CodeWithPathPrefixCondition(StateConstants.STATE, List.of(BODY_PARTS, HEAD)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof StringAttribute state) {
            String headState = state.getValue();
            StringAttribute visionDistance = battleModel.getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .get(Attributes.VISION_DISTANCE);

            switch (headState) {
                case StateConstants.GOOD -> visionDistance.setValue(StateConstants.FAR);
                case StateConstants.DAMAGED -> visionDistance.setValue(StateConstants.NEAR);
                case StateConstants.DESTROYED -> visionDistance.setValue(StateConstants.NONE);
                default -> {
                    return List.of();
                }
            }

            return List.of(new PuttedProcessedAttribute(visionDistance));
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributeMarker) {
            StringAttribute visionDistance = battleModel.getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .getAsString(VISION_DISTANCE)
                    .setValue(StateConstants.NONE);

            return List.of(new RemovedProcessedAttribute(visionDistance));
        }

        return List.of();
    }
}
