package com.jgames.survival.view.core.displays;

import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public interface ConstraintDisplay extends Display {
    void bindElement(UIElementWrapper elementWrapper, String withConstraint);
    void unbindElement(UIElement element);
    ConstraintManager getConstraintManager();
}
