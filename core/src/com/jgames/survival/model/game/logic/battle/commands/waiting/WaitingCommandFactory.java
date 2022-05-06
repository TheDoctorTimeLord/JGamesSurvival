package com.jgames.survival.model.game.logic.battle.commands.waiting;

import ru.jengine.battlemodule.core.BattleContext;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.commands.executioncontexts.NoneParameters;
import ru.jengine.battlemodule.core.models.BattleModel;
import ru.jengine.beancontainer.annotations.Bean;

/**
 * Описывает фабрику ожидания, создающую объект команды.
 * В обязанности фабрики также входит определить может ли конкретный динамический объект
 * выполнять данную команду в бою и доступна ли эта команда объекту в текущем ходу.
 */
@Bean
public class WaitingCommandFactory implements BattleCommandFactory<NoneParameters, WaitingCommand> {
    @Override
    public boolean canExecute(BattleModel model, BattleContext battleContext) {
        return true;
    }

    @Override
    public boolean isAvailableCommand(BattleModel model, BattleContext battleContext) {
        return true;
    }

    @Override
    public WaitingCommand createBattleCommand(BattleModel model, BattleContext battleContext) {
        return new WaitingCommand();
    }
}
