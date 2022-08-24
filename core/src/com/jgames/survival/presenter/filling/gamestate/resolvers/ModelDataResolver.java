package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;

import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;

public interface ModelDataResolver {
    List<ResolvingContext> resolve(List<GameObject> cellGameObjects);
    int getPriority();
}
