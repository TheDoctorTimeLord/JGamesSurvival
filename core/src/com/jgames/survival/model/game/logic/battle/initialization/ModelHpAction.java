package com.jgames.survival.model.game.logic.battle.initialization;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

public class ModelHpAction implements BattleAction {
    private final int modelId;
    private final int hp;

    public ModelHpAction(int modelId, int hp) {
        this.modelId = modelId;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getModelId() {
        return modelId;
    }
}
