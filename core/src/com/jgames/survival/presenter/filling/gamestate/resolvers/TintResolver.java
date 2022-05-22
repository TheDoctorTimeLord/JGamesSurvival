package com.jgames.survival.presenter.filling.gamestate.resolvers;

import java.util.List;

import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.ui.constants.SpecialObjectsNames;

//@Bean
public class TintResolver implements ModelDataResolver {
    @Override
    public List<ResolvingContext> resolve(List<GameObject> cellGameObjects) {
        boolean hasKilled = false;

        for (GameObject cellGameObject : cellGameObjects) {
            HealthComponent healthComponent = cellGameObject.getComponent(HealthComponent.class);
            if (healthComponent == null) {
                continue;
            }

            if (healthComponent.isKilled()) {
                hasKilled = true;
            } else if (hasKilled) {
                hasKilled = false;
                break;
            }
        }

        return hasKilled ? List.of(new ResolvingContext(SpecialObjectsNames.TINT, new DrawingContext())) : List.of();
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
