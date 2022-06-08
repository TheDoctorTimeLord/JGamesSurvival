package com.jgames.survival.presenter.filling.gamestate.mutators;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.presenter.core.gamestate.PresentingStateModule;
import com.jgames.survival.presenter.core.gamestate.PresentingStateModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.DirectionComponent;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.HealthComponent;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.PositionComponent;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.TypeNameComponent;
import com.jgames.survival.presenter.filling.gamestate.modules.GameObjectsModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;

@Bean
public class GameObjectsMutator implements PresentingStateModuleMutator {
    private static final List<String> USED_MODULE_NAMES = List.of(GameObjectsModule.NAME, MapFillingModule.NAME);

    private GameObjectsModule gameObjectsModule;
    private MapFillingModule mapFilling;

    @Override
    public List<String> getUsedModuleNames() {
        return USED_MODULE_NAMES;
    }

    @Override
    public void connectWithModule(PresentingStateModule<?>... modules) {
        gameObjectsModule = (GameObjectsModule) modules[0];
        mapFilling = (MapFillingModule) modules[1];
    }

    public void setPositionData(int objectId, Point startPosition, @Nullable Direction direction) {
        GameObject gameObject = gameObjectsModule.getOrCreateLastObjectState(objectId);
        gameObject.addComponent(new PositionComponent(startPosition));

        if (direction != null) {
            gameObject.addComponent(new DirectionComponent(direction));
        }

        mapFilling.addObjectsOnCell(startPosition, objectId);
        mapFilling.markCellAsUpdated(startPosition);
    }

    public void setObjectType(int objectId, String objectTypeName) {
        gameObjectsModule.getOrCreateLastObjectState(objectId).addComponent(new TypeNameComponent(objectTypeName));
    }

    public void setHp(int objectId, int hp) {
        gameObjectsModule.getOrCreateLastObjectState(objectId).addComponent(new HealthComponent(hp));
    }

    public void moveObject(int objectId, Point newPosition) {
        GameObject gameObject = gameObjectsModule.getLastObjectState(objectId);
        gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
            Point lastPosition = positionComponent.getPosition();

            positionComponent.setPosition(newPosition);

            mapFilling.updateObjectsOnCell(objectId, lastPosition, newPosition);
            mapFilling.markCellAsUpdated(lastPosition);
            mapFilling.markCellAsUpdated(newPosition);
        });
    }

    public void rotateObject(int objectId, Direction newDirection) {
        GameObject gameObject = gameObjectsModule.getLastObjectState(objectId);
        gameObject.computeIfContains(DirectionComponent.class, directionComponent -> {
            directionComponent.setDirection(newDirection);
            gameObject.computeIfContains(PositionComponent.class, positionComponent ->
                    mapFilling.markCellAsUpdated(positionComponent.getPosition()));
        });
    }

    public void damageObject(int modelId, int damagePoints) {
        GameObject gameObject = gameObjectsModule.getLastObjectState(modelId);
        gameObject.computeIfContains(HealthComponent.class, healthComponent -> {
            healthComponent.damage(damagePoints);
            gameObject.computeIfContains(PositionComponent.class, positionComponent ->
                    mapFilling.markCellAsUpdated(positionComponent.getPosition()));
        });
    }

    public void killObject(int modelId) {
        GameObject gameObject = gameObjectsModule.getLastObjectState(modelId);
        gameObject.computeIfContains(HealthComponent.class, healthComponent -> {
            healthComponent.kill();
            gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
                mapFilling.markCellAsUpdated(positionComponent.getPosition());
            });
        });
    }

    public void startNewPhase() {
        gameObjectsModule.addState();
        mapFilling.addState();
    }
}
