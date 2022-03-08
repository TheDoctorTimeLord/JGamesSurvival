package com.jgames.survival.model.game.logic.battle.behaviours;

import com.google.common.collect.Multimap;
import com.jgames.survival.model.game.logic.battle.commands.WaitCommand;
import ru.jengine.battlemodule.core.behaviors.Behavior;
import ru.jengine.battlemodule.core.behaviors.BehaviorObjectsManager;
import ru.jengine.battlemodule.core.commands.AdditionalBattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.information.InformationCenter;
import ru.jengine.battlemodule.core.models.BattleModel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Определяет поведение ожидания.
 */
public class WaitingBehavior implements Behavior {

    /**
     * Определяет какими динамическими объектами в игре управляет поведение ожидания.
     * @param dynamicObjects список доступных для выбора динамических моделей.
     * @param informationCenter объект, предоставляющий данные боя, доступные персонажу.
     * @return список ID объектов, которыми будет управлять поведение ожидания.
     */
    @Override
    public Set<Integer> bind(List<BattleModel> dynamicObjects, InformationCenter informationCenter) {
        return dynamicObjects.stream()
                .map(BattleModel::getId)
                .collect(Collectors.toSet());
    }

    /**
     * Отменяет управление переданным динамическим объект
     * @param unboundedId ID динамического объекта
     */
    @Override
    public void unbind(int unboundedId) {
    }

    /**
     * Выбирает действие, которое будет выполнять указанный динамический объект
     * @param characterId ID динамического объекта, выполняющего действие
     * @param availableCommands доступные для выбора команды
     * @return действие, которое будет исполнено указанным динамическим объектом
     */
    @Override
    public BattleCommandPerformElement<?> sendAction(int characterId, List<BattleCommand<?>> availableCommands) {
       WaitCommand waitCommand = (WaitCommand) availableCommands.stream()
                .filter((battleCommand -> battleCommand instanceof WaitCommand))
                .findFirst()
                .orElse(null);
        NoneParameters noneParameters = waitCommand.createParametersTemplate();
        return new BattleCommandPerformElement<>(characterId, waitCommand, noneParameters);
    }

    /**
     * Уточняет параметры для дополнительного действия, выполняемого динамическим объектом
     * @return null - нет дополнительных действий.
     */
    @Override
    public BattleCommandPerformElement<?> handleAdditionalCommand(int characterId, AdditionalBattleCommand<?> command) {
        return null;
    }
}
