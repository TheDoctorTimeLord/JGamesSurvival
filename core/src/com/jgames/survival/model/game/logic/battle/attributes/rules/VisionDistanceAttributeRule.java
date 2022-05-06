package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.ATTRIBUTES;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.VISION_DISTANCE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.BODY_PARTS;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.HEAD;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.CAN_VISION;
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
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.RemovedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue;
import com.jgames.survival.model.game.logic.battle.attributes.constants.VisionDistance;

/**
 * Правило, по которому изменяется атрибут visionDistance некоторой модели на поле боя
 */
@BattleBeanPrototype
public class VisionDistanceAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(CAN_VISION, List.of(FEATURES)),
                new CodeWithPathPrefixCondition(STATE, List.of(BODY_PARTS, HEAD)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof IntAttribute state) {
            StateValue headState = StateValue.resolveByOrdinal(state.getValue());
            IntAttribute visionDistance = battleModel.getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .get(Attributes.VISION_DISTANCE);

            if (headState != null) {
                visionDistance.setValue(mapToVisionDistance(headState));
                return List.of(new PuttedProcessedAttribute(visionDistance));
            }
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(BattleModel battleModel, BattleAttribute battleAttribute) {
        if (battleAttribute instanceof AttributeMarker) {
            IntAttribute visionDistance = battleModel.getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .getAsInt(VISION_DISTANCE)
                    .setValue(VisionDistance.NONE.ordinal());

            return List.of(new RemovedProcessedAttribute(visionDistance));
        }

        return List.of();
    }

    private static int mapToVisionDistance(StateValue state) {
        return switch (state) {
            case GOOD -> VisionDistance.FAR.ordinal();
            case DAMAGED -> VisionDistance.NEAR.ordinal();
            default -> VisionDistance.NONE.ordinal();
        };
    }
}
