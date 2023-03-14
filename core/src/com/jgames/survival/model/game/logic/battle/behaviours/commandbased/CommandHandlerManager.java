package com.jgames.survival.model.game.logic.battle.behaviours.commandbased;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        public <T extends BattleCommand<?>> MainCommandHandlerBuilder bindCommand(Class<T> boundedTo,
                CommandHandlerFunction<T> handler)
        {
            commandHandlers.add(new CommonCommandHandler(boundedTo, handler));
            return this;
        }
        public MainCommandHandler build(DefaultCommandHandlerFunction defaultHandler) {
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

        @SuppressWarnings("unchecked")
        public BattleCommandPerformElement<?> handle(int characterId, Map<Class<? extends BattleCommand<?>>, BattleCommand<?>> availableCommands) {
            for (CommonCommandHandler commandHandler : commandHandlers) {
                BattleCommand<?> handlingCommand = commandHandler.getHandlingCommand(availableCommands);
                if (handlingCommand != null) {
                    return ((CommandHandlerFunction<BattleCommand<?>>)commandHandler.handler).handle(characterId, handlingCommand);
                }
            }
            return defaultHandler.handler.handle(characterId, null);
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

    @FunctionalInterface
    public interface CommandHandlerFunction<C extends BattleCommand<?>> {
        BattleCommandPerformElement<?> handle(Integer id, C command);
    }

    private static class CommonCommandHandler extends CommandHandler<CommandHandlerFunction<?>> {

        private CommonCommandHandler(Class<? extends BattleCommand<?>> commandClass, CommandHandlerFunction<?> handler) {
            super(commandClass, handler);
        }
    }

    @FunctionalInterface
    public interface DefaultCommandHandlerFunction {
        BattleCommandPerformElement<?> handle(Integer id, List<BattleCommand<?>> command);
    }

    private static class DefaultCommandHandler extends CommandHandler<DefaultCommandHandlerFunction> {
        private DefaultCommandHandler(DefaultCommandHandlerFunction handler) {
            super(null, handler);
        }
    }
}
