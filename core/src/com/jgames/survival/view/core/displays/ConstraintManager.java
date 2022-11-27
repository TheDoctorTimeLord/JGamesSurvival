package com.jgames.survival.view.core.displays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.jgames.survival.view.core.UIRuntimeException;

@Bean
public class ConstraintManager {
    private final Map<String, Constraint> constraints = new HashMap<>();

    public ConstraintManager registerConstraint(String constraintName, Constraint constraint) {
        constraints.put(constraintName, constraint);
        return this;
    }

    public Constraint getConstraint(String constraintName) {
        return constraints.computeIfAbsent(constraintName, n -> {
            throw new UIRuntimeException("Constraint [%s] was not found".formatted(n));
        });
    }

    public Constraint createCompositeConstraint(String compositeConstraintName, String... innerConstraintNames) {
        return new CompositeConstraint(compositeConstraintName, Arrays.stream(innerConstraintNames)
                .map(this::getConstraint)
                .toArray(Constraint[]::new)
        );
    }

    public static class CompositeConstraint implements Constraint {
        private final String constraintName;
        private final Constraint[] constraints;

        public CompositeConstraint(String constraintName, Constraint... constraints) {
            this.constraintName = constraintName;
            this.constraints = constraints;
        }

        @Override
        public void applyResizedConstraint(Actor actor, Group parent, int windowWidth, int windowHeight) {
            for (Constraint constraint : constraints) {
                constraint.applyResizedConstraint(actor, parent, windowWidth, windowHeight);
            }
        }

        @Override
        public String getConstraintName() {
            return constraintName;
        }
    }
}
