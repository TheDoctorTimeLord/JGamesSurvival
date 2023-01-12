package com.jgames.survival.viewmodel.impl.changeshandling;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.phase.BattleActionWrapper;
import com.jgames.survival.viewmodel.core.changeshangling.GameChangeApplier;
import com.jgames.survival.viewmodel.core.changeshangling.BaseGameChangeHandler;
import com.jgames.survival.viewmodel.core.viewstate.PresentingViewState;

@Bean
public class BattleActionWrapperHandler extends BaseGameChangeHandler {
    private final List<BattleActionHandler<BattleAction>> battleActionHandlers;

    public BattleActionWrapperHandler(GameChangeApplier applier, List<BattleActionHandler<BattleAction>> battleActionHandlers) {
        super(applier);
        this.battleActionHandlers = battleActionHandlers;
    }

    @Override
    public boolean needNotify(GameChange information) {
        return information instanceof BattleActionWrapper;
    }

    @Nullable
    @Override
    protected Runnable createApplyingTask(GameChange information) {
        BattleAction battleAction = ((BattleActionWrapper)information).getWrapped();
        for (BattleActionHandler<BattleAction> battleActionHandler : battleActionHandlers) {
            if (battleActionHandler.canHandle(battleAction)) {
                return battleActionHandler.createApplyingTask(battleAction);
            }
        }

        Gdx.app.error("ACTION HANDLING", "[" + battleAction + "] was not handled");
        return null;
    }

    @Override
    public void setGameState(PresentingViewState presentingViewState) {
        for (BattleActionHandler battleActionHandler : battleActionHandlers) {
            battleActionHandler.setGameState(presentingViewState);
        }
    }
}
