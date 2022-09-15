package com.jgames.survival.view.core.uielements.displaies;

import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public interface ConstraintDisplay extends Display {
    void addElement(UIElementWrapper elementWrapper, Constraint withConstraint);
    void removeElement(UIElement element);
    ConstraintManager getConstraintManager();
}
