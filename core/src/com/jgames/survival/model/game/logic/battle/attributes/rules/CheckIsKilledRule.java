package com.jgames.survival.model.game.logic.battle.attributes.rules;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;

import java.util.List;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.AttributeRule;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.ChangedAttributesContext;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.CodeWithPathSuffixCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.handlingconditions.HandlingCondition;
import ru.jengine.battlemodule.standardfilling.battleattributes.attributerules.processedattributes.AbstractProcessedAttribute;

import com.jgames.survival.model.game.logic.battle.events.deadmodel.DeadDynamicModelEvent;

@BattleBeanPrototype
public class CheckIsKilledRule implements AttributeRule {
    @Override
    public List<HandlingCondition> getHandledAttributeCodes() {
        return List.of(new CodeWithPathSuffixCondition(Attributes.HIT_POINTS, List.of(Attributes.ATTRIBUTES)));
    }

    @Override
    public List<AbstractProcessedAttribute> processPuttedAttribute(ChangedAttributesContext context) {
        if (context.getChangedAttribute() instanceof IntAttribute hp && hp.getValue() <= 0) {
            context.getDispatcher().handle(new DeadDynamicModelEvent(context.getModel().getId()));
        }

        return List.of();
    }

    @Override
    public List<AbstractProcessedAttribute> processRemovedAttribute(ChangedAttributesContext context) {
        return List.of();
    }
}
