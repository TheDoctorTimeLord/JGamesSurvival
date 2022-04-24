package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

public class AvailableTypeNamesAction implements BattleAction {
    private final List<String> availableTypeNames;

    public AvailableTypeNamesAction(List<String> availableTypeNames) {
        this.availableTypeNames = availableTypeNames;
    }

    public List<String> getAvailableTypeNames() {
        return availableTypeNames;
    }
}
