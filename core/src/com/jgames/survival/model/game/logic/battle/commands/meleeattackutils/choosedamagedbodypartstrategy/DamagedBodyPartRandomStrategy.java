package com.jgames.survival.model.game.logic.battle.commands.meleeattackutils.choosedamagedbodypartstrategy;

import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.AttributesConstants;
import com.jgames.survival.model.game.logic.attributes.characterStringAttributes.StateConstants;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.utils.RandomUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Описывает выбора части тела противника, по которой будет наноситься удар
 * Стратегия: выбор части тела производиться произвольным образом
 */
public class DamagedBodyPartRandomStrategy {
    /**
     * Возвращает часть тела противника, по которой будет наноситься удар
     * @param enemy противник
     * @return часть тела, по которой будет наноситься удар, или null,
     * если все части тела противника максимально повреждены и урон нанести уже нельзя
     */
    @Nullable
    public static String chooseDamagedBodyPart(BattleModel enemy) {
        List<String> bodyParts = new ArrayList<>(
                List.of("HEAD", "BODY", "LEFT_ARM", "RIGHT_ARM", "LEFT_LEG", "RIGHT_LEG"));
        while(bodyParts.size() > 0) {
            String bodyPart = chooseRandomBodyPart(bodyParts);
            String bodyPartState = enemy
                    .getAttributes()
                    .getAsContainer(AttributesConstants.BodyPartsConstants.BODY_PARTS)
                    .getAsContainer(bodyPart)
                    .getAsString(StateConstants.STATE).getValue();
            if (StateConstants.DESTROYED.equals(bodyPartState)) {
                bodyParts.remove(bodyPart);
            }
            else {
                return bodyPart;
            }
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
