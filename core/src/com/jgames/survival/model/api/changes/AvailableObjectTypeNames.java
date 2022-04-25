package com.jgames.survival.model.api.changes;

import com.jgames.survival.model.api.GameChange;

import java.util.Collection;

/**
 * Имена типов объектов.
 */
public class AvailableObjectTypeNames implements GameChange {
    private final Collection<String> availableTypeNames;

    public AvailableObjectTypeNames(Collection<String> availableTypeNames) {
        this.availableTypeNames = availableTypeNames;
    }

    public Collection<String> getAvailableTypeNames() {
        return availableTypeNames;
    }
}
