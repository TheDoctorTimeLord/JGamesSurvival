package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;
import java.util.Objects;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

public class TintResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        boolean hasKilled = cellGameObjects.stream()
                .map(gameObject -> gameObject.getComponent(HealthComponent.class))
                .filter(Objects::nonNull)
                .anyMatch(HealthComponent::isKilled);

        return hasKilled ? List.of(new ResolvingContext(SpecialObjectsNames.TINT, new DrawingContext())) : List.of();
    }
}
