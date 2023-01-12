package com.jgames.survival.view.core.displays;

import com.jgames.survival.view.core.uielements.UIElement;

public interface ConstraintDisplay extends Display {
    void bindElement(UIElement element, String withConstraint);
    void unbindElement(UIElement element);
    ConstraintManager getConstraintManager();
}
