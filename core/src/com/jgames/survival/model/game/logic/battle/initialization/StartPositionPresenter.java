package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.List;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.ExtendedBattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.DynamicModel;
import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

/**
 * Класс, генерирующий список действий в бою после инициализации боя.
 */
@BattleBeanPrototype
public class StartPositionPresenter implements InitializationPresenter {
    /**
     * Предоставляет события, которые были собраны за период инициализации боя
     * @param extendedBattleContext расширенный контекст боя
     * @return список действий в бою, либо пустой список, если никаких действий не произошло
     */
    @Override
    public List<BattleAction> presentBattleInitialize(ExtendedBattleContext extendedBattleContext) {
        BattleState battleState = extendedBattleContext.getBattleContext().getBattleState();
        return battleState.getDynamicObjects().stream()
                .filter(model -> model instanceof DynamicModel)
                .map(model -> (DynamicModel)model)
                .filter(model -> model.hasPosition() && model.hasDirection())
                .map(model -> new StartPositionAction(model.getId(), model.getPosition(), model.getDirection()))
                .collect(Collectors.toList());
    }
}
