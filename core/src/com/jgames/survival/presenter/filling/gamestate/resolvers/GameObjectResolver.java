package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.TypeNameComponent;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.constants.HardcodeObjectNames;

@Bean
public class GameObjectResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        List<ResolvingContext> resolvedContexts = new ArrayList<>();

        for (GameObject cellGameObject : cellGameObjects) {
            HealthComponent healthComponent = cellGameObject.getComponent(HealthComponent.class);
            if (healthComponent != null)  {
                if (healthComponent.isKilled()) {
                    resolvedContexts.add(new ResolvingContext(cellGameObject, Constants.DEAD_BODY));
                } else {
                    cellGameObject.computeIfContains(TypeNameComponent.class, c ->
                            resolvedContexts.add(new ResolvingContext(cellGameObject, c.getTypeName())));
                    resolvedContexts.add(new ResolvingContext(cellGameObject, HardcodeObjectNames.PERSON_HP));
                    resolvedContexts.add(new ResolvingContext(cellGameObject, HardcodeObjectNames.PERSON_TEAM));
                }
            } else {
                cellGameObject.computeIfContains(TypeNameComponent.class, component ->
                        resolvedContexts.add(new ResolvingContext(cellGameObject, component.getTypeName())));
            }
        }

        return resolvedContexts;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
