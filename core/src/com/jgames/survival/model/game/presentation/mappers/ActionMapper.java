package com.jgames.survival.model.game.presentation.mappers;

import java.util.stream.Stream;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.jgames.survival.model.api.GameChange;

public interface ActionMapper {
    int getMapperPriority();
    boolean isCanMap(BattleAction battleAction);
    Stream<GameChange> map(BattleAction battleAction);
}
