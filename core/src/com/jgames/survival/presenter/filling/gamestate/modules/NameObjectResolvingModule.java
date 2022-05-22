package com.jgames.survival.presenter.filling.gamestate.modules;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.presenter.filling.gamestate.resolvers.ModelDataResolver;

@Bean
public class NameObjectResolvingModule implements PresentingStateModule<NameObjectResolvingPresenter>, NameObjectResolvingPresenter {
    public static final String NAME = "nameObjectResolving";
    private final List<ModelDataResolver> modelDataResolvers;

    public NameObjectResolvingModule(List<ModelDataResolver> modelDataResolvers) {
        this.modelDataResolvers = modelDataResolvers.stream()
                .sorted(Comparator.comparingInt(ModelDataResolver::getPriority))
                .collect(Collectors.toList());
    }

    /**
     * Зарегистрировать резолвера метаданных.
     */
    public void registerModelDataResolver(ModelDataResolver modelDataResolver) {
        modelDataResolvers.add(modelDataResolver);
    }

    @Override
    public List<ResolvingContext> resolveModelData(List<GameObject> gameObjectCollection) {
        return modelDataResolvers
                .stream()
                .map(modelDataResolver -> modelDataResolver.resolve(gameObjectCollection))
                .flatMap(List::stream)
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
