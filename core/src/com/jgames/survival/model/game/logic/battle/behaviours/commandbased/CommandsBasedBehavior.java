package com.jgames.survival.model.game.logic.battle.behaviours.commandbased;

import java.util.List;

import ru.jengine.battlemodule.core.behaviors.Behavior;
import ru.jengine.battlemodule.core.commands.AdditionalBattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;

public abstract class CommandsBasedBehavior implements Behavior {
    private final CommandHandlerManager commandHandlerManager;

    protected CommandsBasedBehavior(CommandHandlerManager.MainCommandHandler mainCommandHandler) {
        this.commandHandlerManager = new CommandHandlerManager(mainCommandHandler);
    }

    @Override
    public BattleCommandPerformElement<?> sendAction(int characterId, List<BattleCommand<?>> availableCommands) {
        return commandHandlerManager.handler(characterId, availableCommands);
    }

    @Override
    public BattleCommandPerformElement<?> handleAdditionalCommand(int characterId, AdditionalBattleCommand<?> command) {
        return null;
    }
}
