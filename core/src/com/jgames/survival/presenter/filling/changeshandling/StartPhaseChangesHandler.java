package com.jgames.survival.presenter.filling.changeshandling;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.phase.NewPhase;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.GameObjectsMutator;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

@Bean
public class StartPhaseChangesHandler implements GameChangeHandler {
    private GameObjectsMutator personData;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        this.personData = presentingGameState.getModuleMutator(GameObjectsMutator.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof NewPhase;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        personData.startNewPhase();
    }
}
