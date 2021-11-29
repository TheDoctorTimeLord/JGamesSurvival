package com.jgames.survival.model;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;

import com.jgames.survival.model.api.GameAction;
import com.jgames.survival.model.api.GameActionHandler;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.actionhandlers.GameActionHandlersManager;
import com.jgames.survival.model.game.configurations.MainModule;
import com.jgames.survival.model.game.logic.GameBattleHandler;

public class MainGameHandler extends AbstractGameHandler implements GameChangeSender {
    private final GameActionHandlersManager actionHandlersManager;
    private final JEngineContainer gameContainer;
    private final GameBattleHandler battleHandler;

    public MainGameHandler(GameConfiguration configuration) {
        setName("Main logic game thread");

        gameContainer = new JEngineContainer();
        gameContainer.initializeCommonContexts(ContainerConfiguration.build(MainModule.class).addAdditionalBean(this));

        actionHandlersManager = gameContainer.getBean(GameActionHandlersManager.class);

        battleHandler = new GameBattleHandler(gameContainer);
    }

    @Override
    public void run() {
        while (isGameRunning) {
            while (!actionPool.isEmpty()) {
                GameAction action = actionPool.poll();
                GameActionHandler<GameAction> handler = actionHandlersManager.findHandler(action);
                handler.handle(action);
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
        changesPublisher.notify(gameChange);
    }
}
