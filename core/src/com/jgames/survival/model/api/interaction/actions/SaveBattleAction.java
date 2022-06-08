package com.jgames.survival.model.api.interaction.actions;

import java.io.File;

import com.jgames.survival.model.api.interaction.GameAction;

public class SaveBattleAction implements GameAction {
    private final File saveTarget;

    public SaveBattleAction(File saveTarget) {
        this.saveTarget = saveTarget;
    }

    public File getSaveTarget() {
        return saveTarget;
    }
}
