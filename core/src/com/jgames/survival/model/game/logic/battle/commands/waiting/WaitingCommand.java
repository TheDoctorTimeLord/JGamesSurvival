package com.jgames.survival.model.game.logic.battle.commands.waiting;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.models.BattleModel;

import com.jgames.survival.model.game.logic.battle.commands.BattleCommandPriority;
import com.jgames.survival.model.game.logic.battle.commands.NoneParameterizedBattleCommand;

/**
 * Описывает команду ожидания, которую будет исполнять динамический объект.
 */
public class WaitingCommand implements NoneParameterizedBattleCommand {
    @Override
    public void perform(BattleModel model, BattleContext battleContext, NoneParameters executionParameters) {
    }

    @Override
    public int getPriority() {
        return BattleCommandPriority.WAIT.getPriority();
    }
}
