package com.jgames.survival.presenter.filling.changeshandling;

import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandler;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.filling.gamestate.model.ObjectType;
import com.jgames.survival.presenter.filling.gamestate.modules.GameObjectModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectPresenter;
import com.jgames.survival.utils.pubsub.Publisher;
import com.jgames.survival.utils.pubsub.Subscriber;

public class AvailableGameObjectsHandler implements GameChangeHandler {
    public GameObjectPresenter gameObjectModule;

    @Override
    public void setGameState(PresentingGameState presentingGameState) {
        gameObjectModule = presentingGameState.getModulePresenter(GameObjectModule.NAME);
    }

    @Override
    public boolean needNotify(GameChange gameChange) {
        return gameChange instanceof ObjectType;
    }

    @Override
    public void notify(GameChange information, Publisher<GameChange, ? extends Subscriber<GameChange>> source) {
        ObjectType objectType = (ObjectType) information;
        gameObjectModule.addObject(objectType.getId(), objectType);
    }
}
