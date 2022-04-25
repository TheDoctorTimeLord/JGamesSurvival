package com.jgames.survival.model.api.changes.initialize;

import java.util.Collection;

import com.jgames.survival.model.api.GameChange;

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

    @Override
    public String toString() {
        return "AvailableObjectTypeNames {'%s'}".formatted(String.join("', '", availableTypeNames));
    }
}
