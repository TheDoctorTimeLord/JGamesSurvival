package com.jgames.survival.model.game.logic;

import ru.jengine.battlemodule.core.BattleGenerator;
import ru.jengine.battlemodule.core.state.BattleState;

public class BattleStateBasedGenerator extends BattleGenerator {
    private final BattleState battleState;

    public BattleStateBasedGenerator(BattleState battleState) {
        this.battleState = battleState;
    }

    @Override
    public BattleState generate() {
        return battleState;
    }
}
