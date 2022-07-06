package com.jgames.survival.model.game.logic.battle.commands;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.battlemodule.core.exceptions.BattleException;

public class SelectionFromSetParameters<T> implements CommandExecutionParameters {
    private final Set<T> elements;
    private T selectedElement;

    public SelectionFromSetParameters(Set<T> elements) {
        this.elements = elements;
    }

    public Set<T> getElements() {
        return elements;
    }

    public void selectModel(T selectedEnemy) {
        if (!elements.contains(selectedEnemy)) {
            List<String> ids = elements.stream()
                    .map(Object::toString)
                    .toList();
            throw new BattleException("Selected element [%s] is not in [%s]"
                    .formatted(selectedEnemy, String.join(", ", ids)));
        }

        this.selectedElement = selectedEnemy;
    }

    @Nullable
    public T getSelectedElement() {
        return selectedElement;
    }
}
