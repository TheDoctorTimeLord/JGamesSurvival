package com.jgames.survival.presenter.filling.changeshandling;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.MotionChange;
import com.jgames.survival.model.api.changes.MoveChange;
import com.jgames.survival.model.api.changes.RotateChange;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.PersonDataMutator;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class MotionChangesHandler implements GameChangeHandler {
    private PersonDataMutator personDataMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        this.personDataMutator = presentingGameState.getModuleMutator(PersonDataMutator.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof MotionChange;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        if (information instanceof MoveChange) {
            MoveChange change = (MoveChange)information;
            personDataMutator.movePerson(change.getPersonId(), change.getNewPosition());
        } else if (information instanceof RotateChange) {
            RotateChange change = (RotateChange)information;
            personDataMutator.rotatePerson(change.getPersonId(), change.getNewDirection());
        } else {
            Gdx.app.error("CHANGE HANDLING", "[" + information + "] was added but did not handled");
        }
    }
}
