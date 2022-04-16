package com.jgames.survival.model.game.logic.attributes;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants.*;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;

/**
 * Создание и назначение первоначального набора атрибутов для персонажа
 */
public class AttributeGenerator {
    /**
     * Назначение персонажу первоначального набора атрибутов
     * @param character персонаж
     */
    public static void setInitialAttributesKit(DynamicModel character) {
        AttributesContainer characterAttributes = character.getAttributes();
        characterAttributes
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
