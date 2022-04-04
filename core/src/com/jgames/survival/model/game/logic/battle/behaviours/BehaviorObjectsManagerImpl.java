package com.jgames.survival.model.game.logic.battle.behaviours;

import com.google.common.collect.Multimap;
import ru.jengine.battlemodule.core.BattleBeanPrototype;
import ru.jengine.battlemodule.core.behaviors.Behavior;
import ru.jengine.battlemodule.core.behaviors.BehaviorObjectsManager;
import ru.jengine.battlemodule.core.commands.AdditionalBattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;
import ru.jengine.battlemodule.core.information.InformationCenter;
import ru.jengine.battlemodule.core.models.BattleModel;

import java.util.*;

/**
 * Менеджер всех {@link Behavior поведений} в бою. Через него осуществляются вся работа с поведениями динамических
 * объектов в бою
 */
@BattleBeanPrototype
public class BehaviorObjectsManagerImpl implements BehaviorObjectsManager {
    private final List<Behavior> behaviors;
    private final Map<Integer, Behavior> bindings = new HashMap<>();

    public BehaviorObjectsManagerImpl(List<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * Связывает динамические объекты в бою с их поведением. Также происходит дополнительная инициализация поведений
     * менеджером информации в бою.
     * @param allDynamicObjects все динамические объекты в бою
     * @param informationCenter объект, предоставляющий данные боя, доступные персонажу
     */
    @Override
    public void bindBehaviors(List<BattleModel> allDynamicObjects, InformationCenter informationCenter) {
        List<BattleModel> currentModels = new ArrayList<>(allDynamicObjects);

        for (Behavior behavior : behaviors) {
            if (currentModels.isEmpty()) {
                break;
            }
            Set<Integer> bound = behavior.bind(currentModels, informationCenter);

            List<BattleModel> newCurrentModels = new ArrayList<>();
            currentModels.forEach(model -> {
                if (bound.contains(model.getId())) {
                    bindings.put(model.getId(), behavior);
                } else {
                    newCurrentModels.add(model);
                }
            });
            currentModels = newCurrentModels;
        }
    }

    /**
     * Отвязывает поведение от динамического объекта
     * @param dynamicObjectId ID объекта в бою
     */
    @Override
    public void unbindBehavior(int dynamicObjectId) {
        Behavior binding = bindings.remove(dynamicObjectId);
        if (binding != null) {
            binding.unbind(dynamicObjectId);
        }
    }

    /**
     * Получает команду, которую будет выполнять персонаж в этом ходу
     * @param availableCommands сопоставление динамического объекта и списка команд, доступных ему для выполнения
     * @return исполняемая объектом команда
     */
    @Override
    public Collection<BattleCommandPerformElement<?>> extractNewCommands
            (Map<Integer, List<BattleCommand<?>>> availableCommands) {

        Collection<BattleCommandPerformElement<?>> result = new ArrayList<>();

        availableCommands.forEach((id, commands) -> {
            if (!bindings.containsKey(id)) {
                return;
            }

            Behavior behavior = bindings.get(id);
            result.add(behavior.sendAction(id, commands));
        });

        return result;
    }

    /**
     * Получает дополнительную информацию о дополнительном действии текущей команды
     * @param additionalCommands  сопоставление динамического объекта и дополнительных команд, которые он выполняет
     * @return исполняемая объектом дополнительная команда
     */
    @Override
    public Collection<BattleCommandPerformElement<?>> handleAdditionalCommands
            (Multimap<Integer, AdditionalBattleCommand<?>> additionalCommands) {
        Collection<BattleCommandPerformElement<?>> result = new ArrayList<>();

        additionalCommands.asMap().forEach((id, commands) -> commands.forEach(command ->
                        result.add(behaviors.get(id).handleAdditionalCommand(id, command))
                )
        );

        return result;
    }
}
