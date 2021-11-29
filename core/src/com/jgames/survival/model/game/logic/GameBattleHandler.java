package com.jgames.survival.model.game.logic;

import ru.jengine.battlemodule.core.BattleMaster;
import ru.jengine.battlemodule.core.behaviors.BehaviorObjectsManager;
import ru.jengine.battlemodule.core.commands.BattleCommandRegistrar;
import ru.jengine.beancontainer.BeanContainer;

public class GameBattleHandler {
    private final BattleMaster battleMaster;

    public GameBattleHandler(BeanContainer container) {
        battleMaster = container.getBean(BattleMaster.class);
        BattleCommandRegistrar registrar = container.getBean(BattleCommandRegistrar.class);
        BehaviorObjectsManager behaviorsManager = container.getBean(BehaviorObjectsManager.class);

        battleMaster.prepareBattle(new SimpleBattleGenerator(), registrar, behaviorsManager);
    }
}
