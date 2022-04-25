package com.jgames.survival.presenter.filling.gamestate.modules;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.presenter.filling.gamestate.resolvers.ModelDataResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class NameObjectResolvingModule implements PresentingStateModule<NameObjectResolvingPresenter>, NameObjectResolvingPresenter {
    public static final String NAME = "nameObjectResolving";
    private final Collection<ModelDataResolver> modelDataResolvers = new ArrayList<>();

    /**
     * Зарегистрировать резолвера метаданных.
     */
    public void registerModelDataResolver(ModelDataResolver modelDataResolver) {
        modelDataResolvers.add(modelDataResolver);
    }

    @Override
    public Collection<ResolvingContext> resolveModelData(Collection<ModelData> modelDataCollection) {
        return modelDataResolvers
                .stream()
                .map(modelDataResolver -> modelDataResolver.resolve(modelDataCollection))
                .collect(Collectors.toList());
    }

    @Override
    public String getModuleName() {
        return NAME;
    }

    @Override
    public NameObjectResolvingPresenter getPresenter() {
        return this;
    }
}
