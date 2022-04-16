package com.jgames.survival.model.game.logic.attributes.characterStringAttributes;

/**
 * Содержит строковые константы, формирующие дерево атрибутов
 */
public interface AttributesConstants {
    interface BodyPartsConstants {
        String BODY_PARTS = "bodyParts";
        String HEAD = "head";
        String BODY = "body";
        String LEFT_ARM = "leftArm";
        String RIGHT_ARM = "rightArm";
        String LEFT_LEG = "leftLeg";
        String RIGHT_LEG = "rightLeg";
    }

    interface Features {
        String FEATURES = "features";
        String CAN_VISION = "canVision";
        String CAN_MOVE = "canMove";
    }

    interface Attributes {
        String ATTRIBUTES = "attributes";
        String VISION_DISTANCE = "visionDistance";
        String MOVE_DISTANCE = "moveDistance";
        String MELEE_DAMAGE_POINTS = "meleeDamagePoints";
        String HIT_POINTS = "hitPoints";
    }
}
