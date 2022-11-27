package com.jgames.survival.model;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.interaction.GameAction;
import com.jgames.survival.model.api.interaction.GameActionHandler;
import com.jgames.survival.model.api.interaction.GameActionHandlersManager;
import com.jgames.survival.model.api.interaction.GameChange;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.modules.MainModule;
import com.jgames.survival.presenter.filling.jsonloading.CoreResourceLoader;
import com.jgames.survival.utils.GdxLogger;

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
                .addAdditionalBean(new GdxLogger())
                .addAdditionalBean(new CoreResourceLoader())
                .build());

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
        while (isGameRunning) {
            while (!actionPool.isEmpty()) {
                GameAction action = actionPool.poll();
                try {
                    GameActionHandler<GameAction> handler = actionHandlersManager.findHandler(action);
                    handler.handle(action);
                }
                catch (Exception e) {
                    Gdx.app.error("MainGameHandler", "Handling action error", e);
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
    public Logger getLogger() {
        return gameContainer.getBean(Logger.class);
    }

    @Override
    public void sendGameChange(GameChange gameChange) {
        if (gameConfiguration.isDebug()) {
            Gdx.app.log("MainGameHandler_tracer", gameChange.toString());
        }

        changesPublisher.notify(gameChange);
    }

    @Override
    public void stopGame() {
        gameContainer.stop();
        super.stopGame();
    }
}
