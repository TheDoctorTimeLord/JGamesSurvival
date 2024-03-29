package com.jgames.survival.model.game.modules;

import ru.jengine.battlemodule.EnableBattleCoreWithStandardFilling;
import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.beancontainer.service.Constants.Contexts;

@ContainerModule
@Context(value=Contexts.BATTLE_CONTEXT)
@PackageScan("com.jgames.survival.model.game.logic")
@EnableBattleCoreWithStandardFilling
public class MainBattleModule extends AnnotationModule {
}
