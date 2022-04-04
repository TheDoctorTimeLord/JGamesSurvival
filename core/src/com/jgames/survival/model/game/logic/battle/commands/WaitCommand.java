package com.jgames.survival.model.game.logic.battle.commands;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.models.BattleModel;

/**
 * Описывает команду ожидания, которую будет исполнять динамический объект.
 */
public class WaitCommand implements BattleCommand<NoneParameters> {

    /**
     * Создаёт шаблон параметров команды, который нужно заполнить поведению, исполняющему эту команду.
     */
    @Override
    public NoneParameters createParametersTemplate() {
        return NoneParameters.INSTANCE;
    }

    /**
     * Выполняет команду ожидания для переданного динамического объекта, используя при этом переданные параметры команды
     * @param model динамический объект, над которым выполняется команда
     * @param battleContext контекст текущего боя
     * @param executionParameters параметры, уточняющие поведение команды (ТРЕБУЮТ ВАЛИДАЦИИ ПЕРЕД ИСПОЛНЕНИЕМ)
     */
    @Override
    public void perform(BattleModel model, BattleContext battleContext, NoneParameters executionParameters) {
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
