package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

@Bean
public class BackgroundResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        return List.of(new ResolvingContext(null, SpecialObjectsNames.BACKGROUND));
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
