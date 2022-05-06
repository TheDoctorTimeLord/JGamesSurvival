package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.ArrayList;
import java.util.List;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.TypeNameComponent;
import com.jgames.survival.ui.constants.HardcodeObjectNames;

public class GameObjectResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        List<ResolvingContext> resolvedContexts = new ArrayList<>();

        for (GameObject cellGameObject : cellGameObjects) {
            DrawingContext drawingContext = new DrawingContext().setGameObject(cellGameObject);

            cellGameObject.computeIfContains(TypeNameComponent.class, component ->
                    resolvedContexts.add(new ResolvingContext(component.getTypeName(), drawingContext)));
            cellGameObject.computeIfContains(HealthComponent.class, component ->
                    resolvedContexts.add(new ResolvingContext(HardcodeObjectNames.PERSON_HP, drawingContext)));
        }

        return resolvedContexts;
    }
}
