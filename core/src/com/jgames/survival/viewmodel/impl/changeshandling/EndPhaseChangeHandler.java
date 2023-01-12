package com.jgames.survival.viewmodel.impl.changeshandling;

import org.jetbrains.annotations.Nullable;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.phase.NewPhase;
import com.jgames.survival.viewmodel.core.changeshangling.GameChangeApplier;
import com.jgames.survival.viewmodel.core.changeshangling.BaseGameChangeHandler;
import com.jgames.survival.viewmodel.core.viewstate.PresentingViewState;
import com.jgames.survival.viewmodel.impl.viewstate.mutators.ViewChangesQueueSystem;

@Bean
public class EndPhaseChangeHandler extends BaseGameChangeHandler {
    private ViewChangesQueueSystem viewChangesQueueSystem;

    protected EndPhaseChangeHandler(GameChangeApplier gameChangeApplier) {
        super(gameChangeApplier);
    }

    @Override
    public void setGameState(PresentingViewState presentingGameState) {
        viewChangesQueueSystem = presentingGameState.getModuleMutator(ViewChangesQueueSystem.class);
    }

    @Override
    public boolean needNotify(GameChange information) {
        return information instanceof NewPhase;
    }

    @Nullable
    @Override
    protected Runnable createApplyingTask(GameChange information) {
        return () -> viewChangesQueueSystem.markEndPhase();
    }
}
