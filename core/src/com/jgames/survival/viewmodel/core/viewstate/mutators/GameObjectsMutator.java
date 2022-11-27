package com.jgames.survival.viewmodel.core.viewstate.mutators;

import java.util.List;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.viewmodel.core.model.DirectionComponent;
import com.jgames.survival.viewmodel.core.model.GameObject;
import com.jgames.survival.viewmodel.core.model.HealthComponent;
import com.jgames.survival.viewmodel.core.model.PositionComponent;
import com.jgames.survival.viewmodel.core.model.TypeNameComponent;
import com.jgames.survival.viewmodel.core.viewstate.ModuleMutator;
import com.jgames.survival.viewmodel.core.viewstate.ViewStateModule;
import com.jgames.survival.viewmodel.core.viewstate.gameobjects.GameObjectsModule;

@Bean
public class GameObjectsMutator implements ModuleMutator {
    private GameObjectsModule gameObjectsModule;

    @Override
    public List<String> getUsedModuleNames() {
        return List.of(GameObjectsModule.NAME);
    }

    @Override
    public void connectWithModule(ViewStateModule<?>... modules) {
        this.gameObjectsModule = (GameObjectsModule)modules[0];
    }

    public void setPositionData(int objectId, Point startPosition, @Nullable Direction direction) {
        GameObject gameObject = gameObjectsModule.getOrCreateGameObject(objectId);
        gameObject.addComponent(new PositionComponent(startPosition));

        if (direction != null) {
            gameObject.addComponent(new DirectionComponent(direction));
        }

        //TODO обновлять данные на поле
    }

    public void setObjectType(int objectId, String objectTypeName) {
        gameObjectsModule.getOrCreateGameObject(objectId).addComponent(new TypeNameComponent(objectTypeName));
    }

    public void setHp(int objectId, int hp) {
        gameObjectsModule.getOrCreateGameObject(objectId).addComponent(new HealthComponent(hp));
    }

    public void moveObject(int objectId, Point newPosition) {
        GameObject gameObject = gameObjectsModule.getObject(objectId);
        gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
            Point lastPosition = positionComponent.getPosition();
            positionComponent.setPosition(newPosition);

            //TODO обновлять данные на поле
        });
    }

    public void rotateObject(int objectId, Direction newDirection) {
        GameObject gameObject = gameObjectsModule.getObject(objectId);
        gameObject.computeIfContains(DirectionComponent.class, directionComponent -> {
            directionComponent.setDirection(newDirection);
            gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
                //TODO обновлять данные на поле
            });
        });
    }

    public void damageObject(int modelId, int damagePoints) {
        GameObject gameObject = gameObjectsModule.getObject(modelId);
        gameObject.computeIfContains(HealthComponent.class, healthComponent -> {
            healthComponent.damage(damagePoints);
            gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
                //TODO обновлять данные на поле
            });
        });
    }

    public void killObject(int modelId) {
        GameObject gameObject = gameObjectsModule.getObject(modelId);
        gameObject.computeIfContains(HealthComponent.class, healthComponent -> {
            healthComponent.kill();
            gameObject.computeIfContains(PositionComponent.class, positionComponent -> {
                //TODO обновлять данные на поле
            });
        });
    }
}
