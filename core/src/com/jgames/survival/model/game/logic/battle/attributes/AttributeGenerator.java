package com.jgames.survival.model.game.logic.battle.attributes;

import ru.jengine.battlemodule.core.modelattributes.AttributesContainer;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributeMarker;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.AttributesBasedAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Features;
import com.jgames.survival.model.game.logic.battle.attributes.constants.VisionDistance;

/**
 * Создание и назначение первоначального набора атрибутов для персонажа
 */
public class AttributeGenerator {
    /**
     * Первоначальный набор атрибутов для воинов
     */
    public static AttributesContainer getFighterAttributesKit() {
        return new AttributesContainer()
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

    /**
     * Первоначальный набор атрибутов для стрелков
     */
    public static AttributesContainer getArcherAttributesKit() {
        return new AttributesContainer()
                .add(
                        new AttributesBasedAttribute(Features.FEATURES)
                                .add(new AttributeMarker(Features.CAN_VISION))
                                .add(new AttributeMarker(Features.CAN_MOVE))
                                .add(new AttributeMarker(Features.CAN_SHOOT))
                )
                .add(
                        new AttributesBasedAttribute(Attributes.ATTRIBUTES)
                                .add(new IntAttribute(Attributes.VISION_DISTANCE, VisionDistance.FAR.ordinal()))
                                .add(new IntAttribute(Attributes.MOVE_DISTANCE, 3))
                                .add(new IntAttribute(Attributes.DISTANCE_DAMAGE_POINT, 3))
                                .add(new IntAttribute(Attributes.MELEE_DAMAGE_POINTS, 2))
                                .add(new IntAttribute(Attributes.HIT_POINTS, 16))
                );
    }
}
