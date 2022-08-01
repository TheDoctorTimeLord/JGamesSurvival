package com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.modelattributes.baseattributes.IntAttribute;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.RandomUtils;

import com.jgames.survival.model.game.logic.battle.attributes.constants.AttributesConstants.BodyParts;
import com.jgames.survival.model.game.logic.battle.attributes.constants.StateValue;
import com.jgames.survival.model.game.logic.battle.utils.attributes.AttributeFindingUtils;

/**
 * Описывает стратегию выбора части тела противника, по которой будет наноситься удар
 * Стратегия: выбор части тела производиться произвольным образом
 */
@Bean
public class DamagedBodyPartRandomStrategy implements ChooseDamagedBodyPartStrategy{
    /**
     * Возвращает часть тела противника, по которой будет наноситься удар
     * @param enemy противник
     * @return часть тела, по которой будет наноситься удар, или null,
     * если все части тела противника максимально повреждены и урон нанести уже нельзя
     */
    @Nullable
    public String chooseDamagedBodyPart(BattleModel enemy) {
        List<String> availableEnemyBodyParts = BodyParts.bodyParts.stream()
                .filter(bodyPart -> {
                    IntAttribute bodyPartAttr = AttributeFindingUtils.getBodyPartStateAttribute(enemy, bodyPart);
                    return bodyPartAttr != null && !StateValue.DESTROYED.isLessOrEquals(bodyPartAttr.getValue());
                })
                .toList();

        return !availableEnemyBodyParts.isEmpty()
                ? RandomUtils.chooseInCollection(availableEnemyBodyParts)
                : null;
    }
}
