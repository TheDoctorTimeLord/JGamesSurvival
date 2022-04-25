package com.jgames.survival.presenter.filling.gamestate.resolvers;

import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.ModelData;

import java.util.List;

public interface ModelDataResolver {
    ResolvingContext resolve(List<ModelData> cellModelData);
}
