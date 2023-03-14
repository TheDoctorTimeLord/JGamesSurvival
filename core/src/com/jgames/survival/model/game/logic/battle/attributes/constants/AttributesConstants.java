package com.jgames.survival.model.game.logic.battle.attributes.constants;

/**
 * Содержит строковые константы, формирующие дерево атрибутов
 */
public interface AttributesConstants {
    interface Features {
        String FEATURES = "features";
        String CAN_VISION = "canVision";
        String CAN_MOVE = "canMove";
        String CAN_SHOOT = "canShoot";
    }

    interface Attributes {
        String ATTRIBUTES = "attributes";
        String VISION_DISTANCE = "visionDistance";
        String MOVE_DISTANCE = "moveDistance";
        String MELEE_DAMAGE_POINTS = "meleeDamagePoints";
        String HIT_POINTS = "hitPoints";
        String DISTANCE_DAMAGE_POINT = "distanceDamagePoint";
    }
}
