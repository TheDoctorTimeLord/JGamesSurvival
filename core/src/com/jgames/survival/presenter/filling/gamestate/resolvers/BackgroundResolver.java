package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

public class BackgroundResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        return List.of(new ResolvingContext(SpecialObjectsNames.BACKGROUND, new DrawingContext()));
    }
}