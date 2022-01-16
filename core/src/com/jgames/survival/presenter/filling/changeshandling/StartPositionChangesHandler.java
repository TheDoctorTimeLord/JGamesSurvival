package com.jgames.survival.presenter.filling.changeshandling;

import ru.test.annotation.battle.battleactions.initializeBattle.StartPositionAction;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.changes.StartPositionData;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.mutators.PersonDataMutator;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class StartPositionChangesHandler implements GameChangeHandler {
    private PersonDataMutator personDataMutator;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        this.personDataMutator = presentingGameState.getModuleMutator(PersonDataMutator.class);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof StartPositionData;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        StartPositionData startPosition = (StartPositionData)information;
        for (StartPositionAction action : startPosition.getStartPositions()) {
            personDataMutator.addPerson(action.getCharacterId(), action.getCharacterPosition(), action.getCharacterDirection());
        }
    }
}
