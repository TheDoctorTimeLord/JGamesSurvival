package com.jgames.survival.model.game.configurations;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

@ContainerModule
@Context("default")
@PackagesScan(
        @PackageScan("com.jgames.survival.model.game.configurations")
)
public class MainModule extends AnnotationModule {
}
