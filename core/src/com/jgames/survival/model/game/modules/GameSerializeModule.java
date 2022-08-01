package com.jgames.survival.model.game.modules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.beancontainer.service.Constants.Contexts;

@ContainerModule
@Context(Contexts.JSON_CONVERTER_CONTEXT)
@PackagesScan({
        @PackageScan("com.jgames.survival.model.api.gameload"),
        @PackageScan("com.jgames.survival.utils.deserialization")
})
public class GameSerializeModule extends AnnotationModule {
}
