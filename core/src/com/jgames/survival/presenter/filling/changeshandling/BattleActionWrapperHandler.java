package com.jgames.survival.presenter.filling.changeshandling;

import java.util.Arrays;
import java.util.List;

import ru.jengine.battlemodule.core.battlepresenter.BattleAction;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.BattleActionWrapper;
import com.jgames.survival.presenter.core.changeshangling.BattleActionHandler;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class BattleActionWrapperHandler implements GameChangeHandler {
    private final List<BattleActionHandler> battleActionHandlers;

    public BattleActionWrapperHandler(BattleActionHandler... battleActionHandlers) {
        this.battleActionHandlers = Arrays.asList(battleActionHandlers);
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
