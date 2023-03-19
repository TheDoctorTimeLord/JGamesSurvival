package com.jgames.survival.model.game.logic.battle.utils.attributes;


import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.Attributes;

/**
 * Вспомогательный класс для получения атрибутов персонажа
 */
public class AttributeFindingUtils {
    /**
     * Возвращает атрибут, характеризующий здоровье бойца
     */
    @Nullable
    public static IntAttribute getHealthAttribute(BattleModel model) {
        return model.getAttributes().getAttributeByPath(Attributes.ATTRIBUTES, Attributes.HIT_POINTS);
    }

    /**
     * Возвращает атрибут, характеризующий значение силы удара,
     * с которой персонаж может нанести вред противнику
     */
    @Nullable
    public static IntAttribute getMeleeDamageAttribute(BattleModel model) {
        return model.getAttributes().getAttributeByPath(Attributes.ATTRIBUTES, Attributes.MELEE_DAMAGE_POINTS);
    }

    public static IntAttribute getVisionDistance(BattleModel model) {
        return model.getAttributes().getAttributeByPath(Attributes.ATTRIBUTES, Attributes.VISION_DISTANCE);
    }
}
