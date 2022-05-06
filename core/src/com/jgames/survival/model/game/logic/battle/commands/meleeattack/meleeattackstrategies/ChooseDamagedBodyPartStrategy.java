package com.jgames.survival.model.game.logic.battle.commands.meleeattack.meleeattackstrategies;

import ru.jengine.battlemodule.core.models.BattleModel;

/**
 * Описывает стратегию выбора части тела противника, по которой будет наноситься удар
 */
public interface ChooseDamagedBodyPartStrategy {
    /**
     * Возвращает часть тела противника, по которой будет наноситься удар
     * @param enemy объект, у которого выбирается часть тела для нанесения удара
     */
    String chooseDamagedBodyPart(BattleModel enemy);
}
