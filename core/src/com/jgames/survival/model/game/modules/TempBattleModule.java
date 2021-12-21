package com.jgames.survival.model.game.modules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.ImportModule;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.beancontainer.service.Constants;
import ru.test.annotation.battle.TestBattle;

@ContainerModule
@Context(Constants.BATTLE_CONTEXT)
@ImportModule(TestBattle.class)
public class TempBattleModule extends AnnotationModule {
}
