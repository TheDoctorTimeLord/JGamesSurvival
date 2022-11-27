package com.jgames.survival.model.game.modules;

import ru.jengine.beancontainer.Constants.Contexts;
import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

@ContainerModule(contextName = "interaction", beanSources = Contexts.JSON_CONVERTER_CONTEXT)
@PackagesScan({
        @PackageScan("com.jgames.survival.model.api.interaction"),
        @PackageScan("com.jgames.survival.model.game.presentation")
})
public class InteractionWithModelModule extends AnnotationModule {
}
