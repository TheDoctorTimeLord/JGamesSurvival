package com.jgames.survival.presenter.filling.changeshandling;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.initialize.BattleWasReloaded;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.ResetAllModulesMutator;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

@Bean
public class BattleWasReloadedHandler implements GameChangeHandler {
    private ResetAllModulesMutator resetAllModulesMutator;

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof BattleWasReloaded;
    }

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
         resetAllModulesMutator = presentingGameState.getModuleMutator(ResetAllModulesMutator.class);
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        resetAllModulesMutator.resetAllModules();
    }
}
