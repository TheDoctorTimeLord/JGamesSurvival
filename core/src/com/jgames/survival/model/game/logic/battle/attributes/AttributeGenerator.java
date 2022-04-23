package com.jgames.survival.model.game.logic.battle.attributes;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyPartsConstants;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateConstants;

/**
 * Создание и назначение первоначального набора атрибутов для персонажа
 */
public class AttributeGenerator {
    /**
     * Назначение персонажу первоначального набора атрибутов
     */
    public static AttributesContainer getInitialAttributesKit() {
        return new AttributesContainer()
                .add(
                        new AttributesBasedAttribute(BodyPartsConstants.BODY_PARTS)
                                .add(new AttributesBasedAttribute(BodyPartsConstants.HEAD)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                                .add(new AttributesBasedAttribute(BodyPartsConstants.BODY)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                                .add(new AttributesBasedAttribute(BodyPartsConstants.LEFT_ARM)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                                .add(new AttributesBasedAttribute(BodyPartsConstants.RIGHT_ARM)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                                .add(new AttributesBasedAttribute(BodyPartsConstants.LEFT_LEG)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                                .add(new AttributesBasedAttribute(BodyPartsConstants.RIGHT_LEG)
                                        .add(new StringAttribute(StateConstants.STATE, StateConstants.GOOD)))
                )
                .add(
                        new AttributesBasedAttribute(Features.FEATURES)
                                .add(new AttributeMarker(Features.CAN_VISION))
                                .add(new AttributeMarker(Features.CAN_MOVE))
                )
                .add(
                        new AttributesBasedAttribute(Attributes.ATTRIBUTES)
                                .add(new StringAttribute(Attributes.VISION_DISTANCE, StateConstants.FAR))
                                .add(new IntAttribute(Attributes.MOVE_DISTANCE, 3))
                                .add(new IntAttribute(Attributes.MELEE_DAMAGE_POINTS, 4))
                                .add(new IntAttribute(Attributes.HIT_POINTS, 16))
                );
    }
}
