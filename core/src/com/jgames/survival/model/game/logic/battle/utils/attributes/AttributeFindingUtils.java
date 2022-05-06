package com.jgames.survival.model.game.logic.battle.utils.attributes;

import static com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts.Attributes.STATE;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.AttributeUtils;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants;
import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;

/**
 * Вспомогательный класс для получения атрибутов персонажа
 */
public class AttributeFindingUtils {
    /**
     * Возвращает атрибут, характеризующий часть тела персонажа
     * @param model объект, представляющий персонажа в бою
     * @param damagedBodyPart часть тела персонажа
     */
    @Nullable
    public static IntAttribute getBodyPartStateAttribute(BattleModel model, String damagedBodyPart) {
        return AttributeUtils.extractInnerAttribute(
                model.getAttributes(),
                List.of(BodyParts.BODY_PARTS, damagedBodyPart),
                STATE);
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
