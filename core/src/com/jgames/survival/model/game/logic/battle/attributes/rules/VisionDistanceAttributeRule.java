package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.ATTRIBUTES;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes.VISION_DISTANCE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.CAN_VISION;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.FEATURES;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.ChangedAttributesContext;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.VisionDistance;

/**
 * Правило, по которому изменяется атрибут visionDistance некоторой модели на поле боя
 */
@BattleBeanPrototype
public class VisionDistanceAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(CAN_VISION, List.of(FEATURES)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof AttributeMarker) {
            IntAttribute visionDistance = context.getModel().getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .get(Attributes.VISION_DISTANCE);

            return List.of(new PuttedProcessedAttribute(visionDistance.setValue(VisionDistance.FAR.ordinal())));
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof AttributeMarker) {
            IntAttribute visionDistance = context.getModel().getAttributes()
                    .getAsContainer(ATTRIBUTES)
                    .getAsInt(VISION_DISTANCE);

            return List.of(new PuttedProcessedAttribute(visionDistance.setValue(VisionDistance.NONE.ordinal())));
        }

        return List.of();
    }
}
