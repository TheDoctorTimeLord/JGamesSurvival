package com.jgames.survival.model.game.presentation.mappers.initializeactions;

import java.util.stream.Stream;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.initialize.AvailableObjectTypeNames;
import com.jgames.survival.model.game.logic.battle.initialization.AvailableTypeNamesAction;
import com.jgames.survival.model.game.presentation.mappers.InitializeActionMapper;

@Bean
public class AvailableTypeNamesActionMapper implements InitializeActionMapper {
    @Override
    public int getMapperPriority() {
        return 0;
    }

    @Override
    public boolean isCanMap(BattleAction battleAction) {
        return battleAction instanceof AvailableTypeNamesAction;
    }

    @Override
    public Stream<GameChange> map(BattleAction battleAction) {
        AvailableTypeNamesAction action = (AvailableTypeNamesAction)battleAction;
        return Stream.of(new AvailableObjectTypeNames(action.getAvailableTypeNames()));
    }
}
