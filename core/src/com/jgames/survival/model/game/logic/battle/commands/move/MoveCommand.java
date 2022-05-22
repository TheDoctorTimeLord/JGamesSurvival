package com.jgames.survival.model.game.logic.battle.commands.move;

import static com.jgames.survival.model.game.logic.battle.commands.move.MoveCommandFactory.checkCanMove;

import java.util.Set;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.events.DispatcherBattleWrapper;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.battlemodule.core.serviceclasses.Direction;
import ru.jengine.battlemodule.core.serviceclasses.Point;
import ru.jengine.battlemodule.core.state.BattleState;
import ru.jengine.battlemodule.standardfilling.movement.CanMoved;
import ru.jengine.battlemodule.standardfilling.movement.MoveEvent;

import com.jgames.survival.model.game.logic.battle.commands.BattleCommandPriority;
import com.jgames.survival.model.game.logic.battle.events.changedirection.ChangeDirectionEvent;
import com.jgames.survival.utils.MoveUtils;

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
     * Выполняет команду перемещения для переданного динамического объекта, используя при этом переданные параметры
     * команды
     *
     * Проверяется:
     * <ol>
     * <li>По прежнему ли персонажу доступна выбранная позиция для перемещения</li>
     * <li>По прежнему ли персонаж способен перемещаться</li>
     * </ol>
     *
     * Выполняется:
     * <ol>
     * <li>Кидается событие MoveEvent</li>
     * </ol>
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
            Direction oldDirection = canMoved.getDirection();
            Point oldPosition = canMoved.getPosition();
            Point newPosition = moveParameters.getSelectedPosition();
            Direction newDirection = getChangedDirection(oldPosition, newPosition, oldDirection);

            DispatcherBattleWrapper dispatcher = battleContext.getDispatcher();

            if (isAvailablePoint(newPosition, battleState)) {
                dispatcher.handle(new MoveEvent(model.getId(), oldPosition, newPosition));
            }
            dispatcher.handle(new ChangeDirectionEvent(model.getId(), newDirection));
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
     */
    private static Direction getChangedDirection(Point oldPosition, Point newPosition, Direction oldDirection) {
        if (MoveUtils.getDistance(newPosition, oldPosition) > 1) {
            return oldDirection;
        }

        Point offset = newPosition.sub(oldPosition);
        return Direction.getByOffset(offset);
    }

    /**
     * Проверяет, свободна ли клетка на поле боя
     * @param point проверяемая клетка
     * @param battleState хранит всю необходимую информацию о текущем состоянии боя
     * @return true - свободна, false - не свободна
     */
    private static boolean isAvailablePoint(Point point, BattleState battleState) {
        return battleState.getOnPosition(point).isEmpty();
    }

    @Override
    public int getPriority() {
        return BattleCommandPriority.MOVE.getPriority();
    }
}
