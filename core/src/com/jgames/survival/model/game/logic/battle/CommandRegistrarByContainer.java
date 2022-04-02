package com.jgames.survival.model.game.logic.battle;

import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandFactory;
import ru.jengine.battlemodule.core.commands.BattleCommandRegistrar;
import ru.jengine.battlemodule.core.commands.CommandExecutionParameters;
import ru.jengine.beancontainer.annotations.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Класс, отвечающий за регистрацию команд динамических объектов.
 */
@Bean
public class CommandRegistrarByContainer implements BattleCommandRegistrar {
    private final List<BattleCommandFactory<?, ?>> allBattleCommands;

    public CommandRegistrarByContainer(List<BattleCommandFactory<?, ?>> allBattleCommands) {
        this.allBattleCommands = allBattleCommands;
    }

    /**
     * Регистрирует новую команду.
     */
    @Override
    public <P extends CommandExecutionParameters, C extends BattleCommand<P>> void registerCommand(
            BattleCommandFactory<P, C> battleCommandFactory)
    {
        allBattleCommands.add(battleCommandFactory);
    }

    /**
     * Возвращает список всех зарегистрированных команд.
     */
    @Override
    public Collection<BattleCommandFactory<?, ?>> getAllCommands() {
        return new ArrayList<>(allBattleCommands);
    }
}
