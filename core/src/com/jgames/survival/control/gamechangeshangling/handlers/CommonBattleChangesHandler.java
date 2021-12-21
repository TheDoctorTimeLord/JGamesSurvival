package com.jgames.survival.control.gamechangeshangling.handlers;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.control.gamechangeshangling.GameChangeHandler;
import com.jgames.survival.control.gamechangeshangling.PresentingGameState;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.ChangesForPhase;
import com.jgames.survival.model.api.changes.StartPositionData;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class CommonBattleChangesHandler implements GameChangeHandler {
    private PresentingGameState gameState;

    @Override
    public void setGameState(PresentingGameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return true;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        if (information instanceof StartPositionData) {
            //Не надо postRunnable, потому что инициализация происходит в синхронном коде
            gameState.setStartPositions((StartPositionData)information);
        }
        else if (information instanceof ChangesForPhase) {
            ChangesForPhase changesForPhase = (ChangesForPhase)information;
            if (!changesForPhase.getChanges().isEmpty()) {
                Gdx.app.postRunnable(() -> gameState.addChangesToCurrentPhase(changesForPhase));
            }
        }
        else {
            throw new IllegalArgumentException("Not handled change [" + information + "]");
            //TODO надо как-то без эксепшена
        }
    }
}
