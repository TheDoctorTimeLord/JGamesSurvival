package com.jgames.survival.model.game.logic.battle.commands.move;

import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.battlemodule.core.serviceclasses.Point;

import java.util.Collections;
import java.util.Set;

/**
 * Класс, декларирующий, что команда передвижения имеет дополнительный параметр - все доступные клетки для передвижения
 * availablePositions - позиции, доступные для перемещения на них
 * selectedPosition - выбранная игроком позиция
 */
public class MoveParameters implements CommandExecutionParameters {
    private final Set<Point> availablePositions;
    private Point selectedPosition;

    public MoveParameters(Set<Point> availablePositions) {
        this.availablePositions = availablePositions != null ? availablePositions : Collections.emptySet();
    }

    /**
     * Возвращает позиции, доступные для перемещения на них
     */
    public Set<Point> getAvailablePositions() {
        return availablePositions;
    }

    /**
     * Возвращает выбранную игроком позицию
     */
    public Point getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Устанавливает выбранную игроком позицию
     */
    public void setSelectedPosition(Point selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
