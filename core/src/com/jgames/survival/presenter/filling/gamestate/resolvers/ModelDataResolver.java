package com.jgames.survival.presenter.filling.gamestate.resolvers;

import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelData;

import java.util.Collection;

public interface ModelDataResolver {
    ResolvingContext resolve(Collection<ModelData> cellModelData);
}
