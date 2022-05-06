package com.jgames.survival.model.game.logic.battle.commands.rangedattack;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.battlemodule.core.exceptions.BattleException;
import ru.jengine.battlemodule.core.models.BattleModel;

public class RangedAttackParameters implements CommandExecutionParameters {
    private final Set<BattleModel> visibleModels;
    private BattleModel selectedEnemy;

    public RangedAttackParameters(Set<BattleModel> visibleModels) {
        this.visibleModels = visibleModels;
    }

    public Set<BattleModel> getVisibleModels() {
        return visibleModels;
    }

    public void selectEnemy(BattleModel selectedEnemy) {
        if (!visibleModels.contains(selectedEnemy)) {
            List<String> ids = visibleModels.stream()
                    .map(BattleModel::getId)
                    .map(String::valueOf)
                    .toList();
            throw new BattleException("Selected model [%s] is not in [%s]"
                    .formatted(selectedEnemy.getId(), String.join(", ", ids)));
        }

        this.selectedEnemy = selectedEnemy;
    }

    @Nullable
    public BattleModel getSelectedEnemy() {
        return selectedEnemy;
    }
}
