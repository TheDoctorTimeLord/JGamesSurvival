package com.jgames.survival.model.game.logic;

import ru.jengine.battlemodule.core.BattleGenerator;
import ru.jengine.battlemodule.core.BattleMaster;
import ru.jengine.battlemodule.core.behaviors.BehaviorObjectsManager;
import ru.jengine.battlemodule.core.commands.BattleCommandRegistrar;
import ru.jengine.beancontainer.BeanContainer;

import com.jgames.survival.model.GameChangeSender;
import com.jgames.survival.model.game.presentation.ToGameChangeMappersManager;

public class GameBattleHandler {
    private final BeanContainer container;
    private BattleMaster battleMaster;

    public GameBattleHandler(BeanContainer container) {
        this.container = container;
        initializeGame(new InitialBattleGenerator());
    }

    public BattleMaster getBattleMaster() {
        return battleMaster;
    }

    public void onStart() {
        battleMaster.informationAboutInitialize();
    }

    public void reloadGame(BattleGenerator generator) {
        initializeGame(generator);
        onStart();
    }

    private void initializeGame(BattleGenerator generator) {
        setBattleMaster(generator);
        configureMap();
    }

    private void setBattleMaster(BattleGenerator generator) {
        battleMaster = container.getBean(BattleMaster.class);
        BattleCommandRegistrar registrar = container.getBean(BattleCommandRegistrar.class);
        BehaviorObjectsManager behaviorsManager = container.getBean(BehaviorObjectsManager.class);

        battleMaster.prepareBattle(generator, registrar, behaviorsManager);
    }

    private void configureMap() {
        ToGameChangeMappersManager mappersManager = container.getBean(ToGameChangeMappersManager.class);
        mappersManager.configureMappers(container.getBean(GameChangeSender.class), this);
    }
}
