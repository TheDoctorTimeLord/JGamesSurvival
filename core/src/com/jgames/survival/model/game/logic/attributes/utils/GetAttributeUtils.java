package com.jgames.survival.model.game.logic.attributes.utils;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.modelattributes.baseattributes.StringAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateConstants;

/**
 * Вспомогательный класс для получения атрибута, характеризующего часть тела персонажа
 */
public class GetAttributeUtils {

    /**
     * Возвращает атрибут, характеризующий часть тела персонажа
     * @param model объект, представляющий персонажа в бою
     * @param damagedBodyPart часть тела персонажа
     */
    @Nullable
    public static StringAttribute getBodyPartStateAttribute(BattleModel model, String damagedBodyPart) {
        return AttributeUtils.extractInnerAttribute(
                model.getAttributes(),
                List.of(AttributesConstants.BodyPartsConstants.BODY_PARTS, damagedBodyPart),
                StateConstants.STATE);
    }

    /**
     * Возвращает атрибут, характеризующий здоровье бойца
     */
    @Nullable
    public static IntAttribute getHealthAttribute(BattleModel model) {
        return AttributeUtils.extractInnerAttribute(model.getAttributes(),
                List.of(AttributesConstants.Attributes.ATTRIBUTES),
                AttributesConstants.Attributes.HIT_POINTS);
    }

    /**
     * Возвращает атрибут, характеризующий значение силы удара,
     * с которой персонаж может нанести вред противнику
     */
    @Nullable
    public static IntAttribute getMeleeDamageAttribute(BattleModel model) {
        return AttributeUtils.extractInnerAttribute(model.getAttributes(),
                List.of(AttributesConstants.Attributes.ATTRIBUTES),
                AttributesConstants.Attributes.MELEE_DAMAGE_POINTS);

    }
}
