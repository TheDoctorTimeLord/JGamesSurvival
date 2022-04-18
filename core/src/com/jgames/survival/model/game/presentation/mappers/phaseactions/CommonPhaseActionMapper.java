package com.jgames.survival.model.game.presentation.mappers.phaseactions;

import java.util.stream.Stream;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.BattleActionWrapper;
import com.jgames.survival.model.game.presentation.mappers.PhaseActionMapper;

@Bean
public class CommonPhaseActionMapper implements PhaseActionMapper {
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
