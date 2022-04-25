package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.List;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.models.HasPosition;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.HasDirection;

/**
 * Класс, передающий данные о начальном положении всех объектов на поле боя
 */
@BattleBeanPrototype
public class StartPositionPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(BattleContext battleContext) {
        BattleState battleState = battleContext.getBattleState();
        return battleState.getModelsInBattle().stream()
                .filter(model -> model instanceof HasPosition)
                .filter(model -> ((HasPosition)model).hasPosition())
                .map(model -> {
                    Point position = ((HasPosition)model).getPosition();
                    Direction direction = model instanceof HasDirection
                            ? ((HasDirection)model).getDirection()
                            : null;
                    return new StartPositionAction(model.getId(), position, direction);
                })
                .collect(Collectors.toList());
    }
}
