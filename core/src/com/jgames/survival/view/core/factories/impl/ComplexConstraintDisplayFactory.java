package com.jgames.survival.view.core.factories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.jengine.utils.Logger;

import com.jgames.survival.view.core.UIRuntimeException;
import com.jgames.survival.view.core.factories.DisplayFactory;
import com.jgames.survival.view.core.displays.Constraint;
import com.jgames.survival.view.core.displays.ConstraintManager;
import com.jgames.survival.view.core.displays.impl.ComplexConstraintDisplayImpl;

public class ComplexConstraintDisplayFactory implements DisplayFactory<ComplexConstraintDisplayImpl> {
    public static final String AVAILABLE_CONSTRAINT_NAMES = "availableConstraintNames";
    public static final String IS_FILL_SCREEN = "isFillingScreen";

    private final Map<String, Constraint> constraints = new HashMap<>();
    private final Logger logger;

    public ComplexConstraintDisplayFactory(List<Constraint> constraints, Logger logger) {
        this.logger = logger;

        for (Constraint constraint : constraints) {
            String constraintName = constraint.getConstraintName();
            if (constraintName == null) {
                throw new UIRuntimeException("Constraint [%s] has no name".formatted(constraint));
            }
            this.constraints.put(constraintName, constraint);
        }
    }

    @Override
    public ComplexConstraintDisplayImpl buildDisplay(Map<String, Object> properties) {
        List<String> constraintNames = getConstraintNames(properties);
        ConstraintManager constraintManager = new ConstraintManager();

        for (String constraintName : constraintNames) {
            Constraint constraint = constraints.get(constraintName);
            if (constraint != null) {
                constraintManager.registerConstraint(constraintName, constraint);
            } else {
                logger.error("ComplexConstraintDisplayFactory", "Constraint [%s] can not be used".formatted(constraintName));
            }
        }

        boolean isFillScreen = getPropertyOrDefault(IS_FILL_SCREEN, properties, Boolean.FALSE);

        return new ComplexConstraintDisplayImpl(constraintManager, isFillScreen);
    }

    private List<String> getConstraintNames(Map<String, Object> properties) {
        return getPropertyOrDefault(AVAILABLE_CONSTRAINT_NAMES, properties, new ArrayList<>(constraints.keySet()));
    }
}
