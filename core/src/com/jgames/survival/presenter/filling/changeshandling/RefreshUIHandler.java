package com.jgames.survival.presenter.filling.changeshandling;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.initialize.RefreshUI;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.presenter.filling.gamestate.mutators.MapFillingMutator;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMapAction;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

@Bean
public class RefreshUIHandler implements GameChangeHandler {
    private final DispatcherUIScriptMachine scriptMachine;
    private MapFillingMutator mapFillingMutator;

    public RefreshUIHandler(DispatcherUIScriptMachine scriptMachine) {
        this.scriptMachine = scriptMachine;
    }

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        mapFillingMutator = presentingGameState.getModuleMutator(MapFillingMutator.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof RefreshUI;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        Gdx.app.postRunnable(() -> {
            mapFillingMutator.markAllMapAsUpdated();
            scriptMachine.dispatch(new UpdateMapAction(), action -> {});
        });
    }
}
