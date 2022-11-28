package com.jgames.survival.model.game.logic.battle.behaviours.commandbased;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ru.jengine.battlemodule.core.commands.BattleCommand;
import ru.jengine.battlemodule.core.commands.BattleCommandPerformElement;

public class CommandHandlerManager {
    private final MainCommandHandler mainCommandHandler;

    public CommandHandlerManager(MainCommandHandler mainCommandHandler) {
        this.mainCommandHandler = mainCommandHandler;
    }

    @SuppressWarnings("unchecked")
    public BattleCommandPerformElement<?> handler(int characterId, List<BattleCommand<?>> availableCommands) {
        Map<Class<? extends BattleCommand<?>>, BattleCommand<?>> battleCommandByClass = availableCommands.stream()
                .collect(Collectors.toMap(command -> (Class<? extends BattleCommand<?>>)command.getClass(), command -> command));

        return mainCommandHandler.handle(characterId, battleCommandByClass);
    }

    public static class MainCommandHandlerBuilder {
        private final List<CommonCommandHandler> commandHandlers = new ArrayList<>();

        private MainCommandHandlerBuilder() {}

        public static MainCommandHandlerBuilder builder() {
            return new MainCommandHandlerBuilder();
        }

        @SuppressWarnings("unchecked")
        public <T extends BattleCommand<?>> MainCommandHandlerBuilder bindCommand(Class<T> boundedTo,
                BiFunction<Integer, T, BattleCommandPerformElement<?>> handler)
        {
            commandHandlers.add(new CommonCommandHandler(
                    boundedTo,
                    (BiFunction<Integer, BattleCommand<?>, BattleCommandPerformElement<?>>)handler
            ));
            return this;
        }
        public MainCommandHandler build(
                BiFunction<Integer, List<BattleCommand<?>>, BattleCommandPerformElement<?>> defaultHandler)
        {
            return new MainCommandHandler(commandHandlers, new DefaultCommandHandler(defaultHandler));
        }

    }
    public static class MainCommandHandler {
        private final List<CommonCommandHandler> commandHandlers;

        private final DefaultCommandHandler defaultHandler;

        public MainCommandHandler(List<CommonCommandHandler> commandHandlers, DefaultCommandHandler defaultHandler) {
            this.commandHandlers = commandHandlers;
            this.defaultHandler = defaultHandler;
        }

        public BattleCommandPerformElement<?> handle(int characterId, Map<Class<? extends BattleCommand<?>>, BattleCommand<?>> availableCommands) {
            for (CommonCommandHandler commandHandler : commandHandlers) {
                BattleCommand<?> handlingCommand = commandHandler.getHandlingCommand(availableCommands);
                if (handlingCommand != null) {
                    return commandHandler.handler.apply(characterId, handlingCommand);
                }
            }
            return defaultHandler.handler.apply(characterId, null);
        }

    }

    private abstract static class CommandHandler<F> {
        private final Class<? extends BattleCommand<?>> commandClass;
        public final F handler;

        private CommandHandler(Class<? extends BattleCommand<?>> commandClass, F handler) {
            this.commandClass = commandClass;
            this.handler = handler;
        }

        @Nullable
        public BattleCommand<?> getHandlingCommand(
                Map<Class<? extends BattleCommand<?>>, BattleCommand<?>> availableBattleCommands)
        {
            return availableBattleCommands.get(commandClass);
        }
    }

    private static class CommonCommandHandler extends CommandHandler<BiFunction<Integer, BattleCommand<?>, BattleCommandPerformElement<?>>> {
        private CommonCommandHandler(Class<? extends BattleCommand<?>> commandClass,
                BiFunction<Integer, BattleCommand<?>, BattleCommandPerformElement<?>> handler) {
            super(commandClass, handler);
        }
    }

    private static class DefaultCommandHandler extends CommandHandler<BiFunction<Integer, List<BattleCommand<?>>, BattleCommandPerformElement<?>>> {
        private DefaultCommandHandler(BiFunction<Integer, List<BattleCommand<?>>, BattleCommandPerformElement<?>> handler) {
            super(null, handler);
        }
    }
}
