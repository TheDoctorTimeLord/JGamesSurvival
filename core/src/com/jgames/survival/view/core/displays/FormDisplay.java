package com.jgames.survival.view.core.displays;

import com.jgames.survival.view.core.uielements.UIElement;
import com.jgames.survival.view.core.uielements.UIElementWrapper;

public interface FormDisplay extends Display {
    void addElement(UIElementWrapper elementWrapper);
    void addElement(UIElementWrapper elementWrapper, int toIndex);
    void removeElement(UIElement element);
}
