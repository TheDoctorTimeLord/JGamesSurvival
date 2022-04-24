package com.jgames.survival.model.game.logic.battle.initialization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.ExtendedBattleContext;
import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.battlemodule.core.battlepresenter.initializebattle.InitializationPresenter;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.utils.CollectionUtils;

@BattleBeanPrototype
public class ObjectTypesPresenter implements InitializationPresenter {
    @Override
    public List<BattleAction> presentBattleInitialize(ExtendedBattleContext extendedBattleContext) {
        BattleState battleState = extendedBattleContext.getBattleContext().getBattleState();
        return CollectionUtils.concat(
                new AvailableTypeNamesAction(new ArrayList<>(battleState.getAvailableObjectTypeNames())),
                battleState.getModelsInBattle().stream()
                        .map(ObjectTypesPresenter::convertToAction)
                        .collect(Collectors.toList())
        );
    }

    private static ObjectTypeAction convertToAction(BattleModel model) {
        return new ObjectTypeAction(model.getId(), model.getBattleModelType().getObjectTypeName());
    }
}
