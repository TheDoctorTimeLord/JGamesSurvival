package com.jgames.survival.model;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;

import com.badlogic.gdx.Gdx;
import com.jgames.survival.model.api.GameAction;
import com.jgames.survival.model.api.GameActionHandler;
import com.jgames.survival.model.api.GameChange;
import com.jgames.survival.model.api.GameActionHandlersManager;
import com.jgames.survival.model.game.logic.GameBattleHandler;
import com.jgames.survival.model.game.modules.MainModule;
import com.jgames.survival.model.game.presentation.ToGameChangeMappersManager;

public class MainGameHandler extends AbstractGameHandler implements GameChangeSender {
    private final GameActionHandlersManager actionHandlersManager;
    private final JEngineContainer gameContainer;
    private final GameBattleHandler battleHandler;

    public MainGameHandler(GameConfiguration configuration) {
        setName("Main logic game thread");
        setDaemon(true);

        gameContainer = new JEngineContainer();
        gameContainer.initializeCommonContexts(ContainerConfiguration.build(MainModule.class).addAdditionalBean(this));

        battleHandler = new GameBattleHandler(gameContainer);

        actionHandlersManager = gameContainer.getBean(GameActionHandlersManager.class);
        actionHandlersManager.configure(battleHandler);
        ToGameChangeMappersManager mappersManager = gameContainer.getBean(ToGameChangeMappersManager.class);
        mappersManager.configureMappers(this, battleHandler);
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
                    Gdx.app.error("ACTION", "Handling action error", e);
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
        changesPublisher.notify(gameChange);
    }

    @Override
    public void stopGame() {
        gameContainer.stop();
        super.stopGame();
    }
}
