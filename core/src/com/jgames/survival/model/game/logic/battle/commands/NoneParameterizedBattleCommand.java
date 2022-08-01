package com.jgames.survival.model.game.logic.battle.commands;

import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;

public interface NoneParameterizedBattleCommand extends BattleCommand<NoneParameters> {
    @Override
    default NoneParameters createParametersTemplate() {
        return NoneParameters.INSTANCE;
    }
}
