package com.jgames.survival.model.game.logic.battle.commands.move;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.movement.CanMoved;
import ru.jengine.battlemodule.standardfilling.movement.MoveEvent;
import ru.test.annotation.battle.events.ChangeDirectionEvent;
import ru.test.annotation.battle.events.TestChangeDirectionEvent;

import java.util.Set;

import static com.jgames.survival.model.game.logic.battle.commands.move.MoveCommandFactory.isAvailablePoint;
import static com.jgames.survival.model.game.logic.battle.commands.move.MoveCommandFactory.checkCanMove;

/**
 * Описывает команду перемещения, которую будет исполнять динамический объект.
 */
public class MoveCommand implements BattleCommand<MoveParameters> {
    private final Set<Point> availablePositions;

    public MoveCommand(Set<Point> availablePositions) {
        this.availablePositions = availablePositions;
    }

    /**
     * Создаёт шаблон параметров команды, который нужно заполнить поведению, исполняющему эту команду.
     */
    @Override
    public MoveParameters createParametersTemplate() {
        return new MoveParameters(availablePositions);
    }

    /**
     * Выполняет команду перемещения для переданного динамического объекта, используя при этом переданные параметры команды
     *
     * Проверяется:
     * По прежнему ли персонажу доступна выбранная позиция для перемещения
     * По прежнему ли персонаж способен перемещаться
     *
     * Выполняется:
     * Кидается событие MoveEvent
     *
     * @param model динамический объект, над которым выполняется команда
     * @param battleContext контекст текущего боя
     * @param moveParameters параметры команды перемещения
     */
    @Override
    public void perform(BattleModel model, BattleContext battleContext, MoveParameters moveParameters) {
        BattleState battleState = battleContext.getBattleState();
        if (isExecutionParametersCorrect(moveParameters, battleState) && checkCanMove(model))
        {
            CanMoved canMoved = (CanMoved)model;
            Direction direction = canMoved.getDirection();
            Point oldPosition = canMoved.getPosition();
            Point newPosition = moveParameters.getSelectedPosition();
            Direction newDirection = getChangedDirection(oldPosition, newPosition, direction);

            DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();

            if (isAvailablePoint(newPosition, battleState)) {
                dispatcher.handle(new MoveEvent(model.getId(), oldPosition, newPosition));
                dispatcher.handle(new ChangeDirectionEvent(model.getId(), newDirection));
            } else {
                dispatcher.handle(new TestChangeDirectionEvent(model.getId(), newDirection));
            }
        }
    }

    /**
     * Проверяет:
     * По прежнему ли персонажу доступна выбранная позиция для перемещения
     * По прежнему ли персонаж способен перемещаться
     *
     * @param moveParameters параметры команды перемещения
     * @param battleState хранит всю необходимую информацию о текущем состоянии боя
     */
    private static boolean isExecutionParametersCorrect(MoveParameters moveParameters, BattleState battleState) {
        Point selectedPoint = moveParameters.getSelectedPosition();
        return moveParameters.getAvailablePositions().contains(selectedPoint) &&
                isAvailablePoint(selectedPoint, battleState);
    }

    /**
     * Возвращает изменённое направление персонажа после его перемещения
     *
     * @param oldPosition текущая позиция персонажа
     * @param newPosition новая выбранная позиция персонажа
     * @param currentDirection текущее направление взгляда персонажа
     */
    private static Direction getChangedDirection(Point oldPosition, Point newPosition, Direction currentDirection) {
        Point offset = newPosition.sub(oldPosition);
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            if (direction.getOffset().equals(offset)) {
                return direction;
            }
        }
        if (offset.getX() < 2 && offset.getY() < 2)
            return currentDirection;
        throw new IllegalArgumentException("Offset [" + offset + "] doesn't match any Direction");
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
