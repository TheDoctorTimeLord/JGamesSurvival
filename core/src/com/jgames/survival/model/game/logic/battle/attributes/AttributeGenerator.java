package com.jgames.survival.model.game.logic.battle.attributes;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue;
import com.jgames.survival.model.game.logic.battle.attributes.constants.VisionDistance;

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
                        new AttributesBasedAttribute(BodyParts.BODY_PARTS)
                                .add(new AttributesBasedAttribute(BodyParts.HEAD)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                                .add(new AttributesBasedAttribute(BodyParts.BODY)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                                .add(new AttributesBasedAttribute(BodyParts.LEFT_ARM)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                                .add(new AttributesBasedAttribute(BodyParts.RIGHT_ARM)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                                .add(new AttributesBasedAttribute(BodyParts.LEFT_LEG)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                                .add(new AttributesBasedAttribute(BodyParts.RIGHT_LEG)
                                        .add(new IntAttribute(STATE, StateValue.GOOD.ordinal())))
                )
                .add(
                        new AttributesBasedAttribute(Features.FEATURES)
                                .add(new AttributeMarker(Features.CAN_VISION))
                                .add(new AttributeMarker(Features.CAN_MOVE))
                )
                .add(
                        new AttributesBasedAttribute(Attributes.ATTRIBUTES)
                                .add(new IntAttribute(Attributes.VISION_DISTANCE, VisionDistance.FAR.ordinal()))
                                .add(new IntAttribute(Attributes.MOVE_DISTANCE, 3))
                                .add(new IntAttribute(Attributes.MELEE_DAMAGE_POINTS, 4))
                                .add(new IntAttribute(Attributes.HIT_POINTS, 16))
                );
    }
}
