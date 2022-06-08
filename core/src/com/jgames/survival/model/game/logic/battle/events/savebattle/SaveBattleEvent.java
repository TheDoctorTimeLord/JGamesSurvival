package com.jgames.survival.model.game.logic.battle.events.savebattle;

import java.io.OutputStream;

import ru.jengine.battlemodule.core.events.BattleEvent;

public class SaveBattleEvent extends BattleEvent {
    private final OutputStream output;

    public SaveBattleEvent(OutputStream output) {
        this.output = output;
    }

    public OutputStream getOutput() {
        return output;
    }
}
