package com.jgames.survival.model.game.modules;

import ru.jengine.beancontainer.Constants.Contexts;
import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

@ContainerModule(contextName = Contexts.JSON_CONVERTER_CONTEXT)
@PackagesScan({
        @PackageScan("com.jgames.survival.model.api.gameload"),
        @PackageScan("com.jgames.survival.utils.deserialization")
})
public class GameSerializeModule extends AnnotationModule {
}
