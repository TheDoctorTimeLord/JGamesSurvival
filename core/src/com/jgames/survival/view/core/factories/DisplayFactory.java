package com.jgames.survival.view.core.factories;

import java.util.Map;

import com.jgames.survival.view.core.uielements.displaies.Display;

public interface DisplayFactory<D extends Display> {
    D buildDisplay(Map<String, Object> properties);
}
