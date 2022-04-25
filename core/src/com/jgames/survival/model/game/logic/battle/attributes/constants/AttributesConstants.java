package com.jgames.survival.model.game.logic.battle.attributes.constants;

import java.util.ArrayList;
import java.util.List;

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
        List<String> bodyParts = new ArrayList<>(List.of
                (HEAD, BODY, LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG));
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
