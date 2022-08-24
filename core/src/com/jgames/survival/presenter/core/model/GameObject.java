package com.jgames.survival.presenter.core.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.badlogic.gdx.Gdx;

public class GameObject {
    private final int objectId;
    private final List<GameObjectComponent> components = new CopyOnWriteArrayList<>();

    public GameObject(int personId) {
        this.objectId = personId;
    }

    public GameObject(GameObject otherObject) {
        this(otherObject.objectId);
        for (GameObjectComponent component : otherObject.components) {
            try {
                components.add(component.clone());
            }
            catch (CloneNotSupportedException e) {
                Gdx.app.error("GameObject", "Component does not support to clone [%s]".formatted(component), e);
            }
        }
    }

    public void addComponent(GameObjectComponent component) {
        components.add(component);
    }

    @Nullable
    public <C extends GameObjectComponent> C getComponent(Class<C> componentClass) {
        for (GameObjectComponent component : components) {
            if (componentClass.isInstance(component)) {
                return (C)component;
            }
        }
        return null;
    }

    public <C extends GameObjectComponent> void computeIfContains(Class<C> componentClass, Consumer<C> callback) {
        C component = getComponent(componentClass);
        if (component != null) {
            callback.accept(component);
        }
    }

    public int getId() {
        return objectId;
    }
}
