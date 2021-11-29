package com.jgames.survival.model.game.configurations;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

@ContainerModule
@Context("interaction")
@PackageScan("com.jgames.survival.model.api")
public class InteractionWithModelModule extends AnnotationModule {
}
