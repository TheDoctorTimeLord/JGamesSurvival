package com.jgames.survival.model.game.presentation.mappers;

import java.util.stream.Stream;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.BattleActionWrapper;

public abstract class CommonActionMapper implements ActionMapper {
    @Override
    public int getMapperPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isCanMap(BattleAction battleAction) {
        return true;
    }

    @Override
    public Stream<GameChange> map(BattleAction battleAction) {
        return Stream.of(new BattleActionWrapper(battleAction));
    }
}
