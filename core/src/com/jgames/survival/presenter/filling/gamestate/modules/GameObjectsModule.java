package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectsPresenter;

@Bean
public class GameObjectsModule implements PresentingStateModule<GameObjectsPresenter>, GameObjectsPresenter {
    public static final String NAME = "modelData";

    private final PhasedFieldWithMap<Integer, GameObject> states =
            new PhasedFieldWithMap<>(GameObject::new, GameObject::new);

    public GameObject getLastObjectState(int objectId) {
        return states.getLastState(objectId);
    }

    public GameObject getOrCreateLastObjectState(int objectId) {
        return states.getOrCreateLastState(objectId);
    }

    public void addState() {
        states.duplicateLastState();
    }

    @Override
    public List<GameObject> getDataForAllObjects() {
        return states.getAllCurrentData();
    }

    @Override
    public GameObject getCurrentObjectState(int objectId) {
        return states.getCurrentState(objectId);
    }

    @Override
    public void updateToNextPhase() {
        states.updateToNextPhase();
    }

    @Override
    public boolean isLastPhase() {
        return states.isLastPhase();
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public GameObjectsPresenter getPresenter() {
        return this;
    }
}
