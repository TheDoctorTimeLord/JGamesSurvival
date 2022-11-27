package com.jgames.survival.view.core.displays;

public interface ComplexConstraintDisplay extends ConstraintDisplay {
    void bindDisplay(Display display, String withConstraint);
    void unbindDisplay(Display display);
}
