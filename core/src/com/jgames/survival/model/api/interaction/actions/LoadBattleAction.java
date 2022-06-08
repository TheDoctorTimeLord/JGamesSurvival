package com.jgames.survival.model.api.interaction.actions;

import java.io.File;

import com.jgames.survival.model.api.interaction.GameAction;

public class LoadBattleAction implements GameAction {
    private final File saveTarget;

    public LoadBattleAction(File saveTarget) {
        this.saveTarget = saveTarget;
    }

    public File getSaveTarget() {
        return saveTarget;
    }
}
