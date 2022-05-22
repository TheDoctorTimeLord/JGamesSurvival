package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
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
            DrawingContext drawingContext = new DrawingContext().setGameObject(cellGameObject);

            HealthComponent healthComponent = cellGameObject.getComponent(HealthComponent.class);
            if (healthComponent != null)  {
                if (healthComponent.isKilled()) {
                    resolvedContexts.add(new ResolvingContext(Constants.DEAD_BODY, drawingContext));
                } else {
                    cellGameObject.computeIfContains(TypeNameComponent.class, c ->
                            resolvedContexts.add(new ResolvingContext(c.getTypeName(), drawingContext)));
                    resolvedContexts.add(new ResolvingContext(HardcodeObjectNames.PERSON_HP, drawingContext));
                }
            } else {
                cellGameObject.computeIfContains(TypeNameComponent.class, component ->
                        resolvedContexts.add(new ResolvingContext(component.getTypeName(), drawingContext)));
            }
        }

        return resolvedContexts;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
