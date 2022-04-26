package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

public class BackgroundResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<ModelData> cellModelData) {
        return List.of(new ResolvingContext(SpecialObjectsNames.BACKGROUND, new DrawingContext()));
    }
}
