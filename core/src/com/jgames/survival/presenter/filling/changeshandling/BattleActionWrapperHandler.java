package com.jgames.survival.presenter.filling.changeshandling;

import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;
import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.api.interaction.changes.BattleActionWrapper;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

@Bean
public class BattleActionWrapperHandler implements GameChangeHandler {
    private final List<BattleActionHandler> battleActionHandlers;

    public BattleActionWrapperHandler(List<BattleActionHandler> battleActionHandlers) {
        this.battleActionHandlers = battleActionHandlers;
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof BattleActionWrapper;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        BattleAction battleAction = ((BattleActionWrapper)information).getWrapped();
        for (BattleActionHandler battleActionHandler : battleActionHandlers) {
            if (battleActionHandler.canHandle(battleAction)) {
                battleActionHandler.handle(battleAction);
                return;
            }
        }

        Gdx.app.error("ACTION HANDLING", "[" + battleAction + "] was not handled");
    }

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        for (BattleActionHandler battleActionHandler : battleActionHandlers) {
            battleActionHandler.setGameState(presentingGameState);
        }
    }
}
