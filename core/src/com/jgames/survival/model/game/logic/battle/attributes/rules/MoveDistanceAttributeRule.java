package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.CAN_MOVE;
import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features.FEATURES;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.ChangedAttributesContext;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathPrefixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.PuttedProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;

/**
 * Правило, по которому изменяется атрибут moveDistance некоторой модели на поле боя
 */
@BattleBeanPrototype
public class MoveDistanceAttributeRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathPrefixCondition(CAN_MOVE, List.of(FEATURES)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof AttributeMarker) {
            BattleModel battleModel = context.getModel();
            IntAttribute moveDistance =  battleModel.getAttributes()
                    .getAsContainer(Attributes.ATTRIBUTES)
                    .getAsInt(Attributes.MOVE_DISTANCE);

            return List.of(new PuttedProcessedAttribute(moveDistance.setValue(3)));
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof AttributeMarker canMove && CAN_MOVE.equals(canMove.getCode())) {
            IntAttribute moveDistance =  context.getModel().getAttributes()
                    .getAsContainer(Attributes.ATTRIBUTES)
                    .getAsInt(Attributes.MOVE_DISTANCE);

            return List.of(new PuttedProcessedAttribute(moveDistance.setValue(0)));
        }

        return List.of();
    }
}
