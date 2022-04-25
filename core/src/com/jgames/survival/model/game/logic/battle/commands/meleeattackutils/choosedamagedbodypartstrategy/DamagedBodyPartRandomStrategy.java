package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.choosedamagedbodypartstrategy;

import com.jgames.survival.model.game.logic.attributes.constants.AttributesConstants;
import com.jgames.survival.model.game.logic.attributes.constants.StateConstants;
import com.jgames.survival.model.game.logic.attributes.utils.GetAttributeUtils;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.RandomUtils;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Описывает стратегию выбора части тела противника, по которой будет наноситься удар
 * Стратегия: выбор части тела производиться произвольным образом
 */
public class DamagedBodyPartRandomStrategy implements ChooseDamagedBodyPartStrategy{
    /**
     * Возвращает часть тела противника, по которой будет наноситься удар
     * @param enemy противник
     * @return часть тела, по которой будет наноситься удар, или null,
     * если все части тела противника максимально повреждены и урон нанести уже нельзя
     */
    @Nullable
    public String chooseDamagedBodyPart(BattleModel enemy) {
        List<String> availableEnemyBodyParts = AttributesConstants.BodyPartsConstants.bodyParts
                .stream()
                .filter(bodyPart -> !StateConstants.DESTROYED.equals(
                        GetAttributeUtils.getBodyPartStateAttribute(enemy, bodyPart).getValue()))
                .toList();
        if (availableEnemyBodyParts.size() > 0) {
            return chooseRandomBodyPart(availableEnemyBodyParts);
        }
        return null;
    }

    /**
     * Возвращает произвольную часть тела
     * @param bodyParts коллекция, описывающая части тела
     */
    private static String chooseRandomBodyPart(List<String> bodyParts) {
        return RandomUtils.chooseInCollection(bodyParts);
    }
}
