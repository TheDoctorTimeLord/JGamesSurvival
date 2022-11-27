package com.jgames.survival.model;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;
import ru.jengine.utils.Logger;

import com.jgames.survival.model.api.interaction.GameAction;
import com.jgames.survival.model.api.interaction.GameActionHandler;
import com.jgames.survival.model.api.interaction.GameActionHandlersManager;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.modules.MainModule;

public class MainGameHandler extends AbstractGameHandler implements GameChangeSender {
    private final GameActionHandlersManager actionHandlersManager;
    private final GameConfiguration gameConfiguration;
    private final JEngineContainer gameContainer;
    private final GameBattleHandler battleHandler;

    public MainGameHandler(GameConfiguration configuration) {
        setName("Main logic game thread");
        setDaemon(true);

        gameConfiguration = configuration;

        gameContainer = new JEngineContainer();
        gameContainer.initializeCommonContexts(ContainerConfiguration.builder(MainModule.class)
                .addAdditionalBean(this)
                .addAdditionalBean(configuration.getLogger())
                .build()
        );

        battleHandler = new GameBattleHandler(gameContainer);

        actionHandlersManager = gameContainer.getBean(GameActionHandlersManager.class);
        actionHandlersManager.configure(battleHandler);
    }

    @Override
    public void onStart() {
        battleHandler.onStart();
    }

    @Override
    public void run() {
        Logger logger = gameConfiguration.getLogger();

        while (isGameRunning) {
            while (!actionPool.isEmpty()) {
                GameAction action = actionPool.poll();
                try {
                    GameActionHandler<GameAction> handler = actionHandlersManager.findHandler(action);
                    handler.handle(action);
                }
                catch (Exception e) {
                    logger.error("MainGameHandler", "Handling action error", e);
                }
            }

            try {
                synchronized (this) {
                    wait();
                }
            }
            catch (InterruptedException e) {
                isGameRunning = false;
            }
        }

        gameContainer.stop();
    }

    @Override
    public void sendGameChange(GameChange gameChange) {
        if (gameConfiguration.isDebug()) {
            gameConfiguration.getLogger().debug("MainGameHandler_tracer", gameChange.toString());
        }

        changesPublisher.notify(gameChange);
    }

    @Override
    public void stopGame() {
        gameContainer.stop();
        super.stopGame();
    }
}
