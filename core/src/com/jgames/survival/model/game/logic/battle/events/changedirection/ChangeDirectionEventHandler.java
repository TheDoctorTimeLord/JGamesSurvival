package com.jgames.survival.model.game.logic.battle.events.changedirection;

import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.BattleEventHandlerPriority;
import ru.jengine.battlemodule.standardfilling.dynamicmodel.HasDirection;

import com.jgames.survival.model.game.logic.battle.events.BaseBattlePostHandler;

public class ChangeDirectionEventHandler extends BaseBattlePostHandler<ChangeDirectionEvent> {
    private final BattleState battleState;

    public ChangeDirectionEventHandler(BattleState battleState) {
        this.battleState = battleState;
    }

    @Override
    public void handle(ChangeDirectionEvent event) {
        BattleModel battleModel = battleState.resolveId(event.getModelId());

        if (battleModel instanceof HasDirection hasDirection) {
            hasDirection.setDirection(event.getNewDirection());
        }
    }

    @Override
    public int getPriority() {
        return BattleEventHandlerPriority.HANDLE.getPriority();
    }
}
