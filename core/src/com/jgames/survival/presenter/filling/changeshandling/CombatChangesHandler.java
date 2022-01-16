package com.jgames.survival.presenter.filling.changeshandling;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.HitChange;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.PersonDataMutator;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class CombatChangesHandler implements GameChangeHandler {
    private PersonDataMutator personData;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        this.personData = presentingGameState.getModuleMutator(PersonDataMutator.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof HitChange;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        HitChange change = (HitChange)information;
        personData.damagePerson(change.getPersonId(), change.getDamage());
    }
}
