package com.jgames.survival.model.game.modules;

import ru.jengine.battlemodule.EnableBattleCoreWithStandardFilling;
import ru.jengine.beancontainer.Constants.Contexts;
import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

@ContainerModule(contextName = Contexts.BATTLE_CONTEXT)
@PackageScan("com.jgames.survival.model.game.logic")
@EnableBattleCoreWithStandardFilling
public class MainBattleModule extends AnnotationModule {
}
