package com.jgames.survival.model.game.modules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.beancontainer.service.Constants;
import ru.jengine.jsonconverter.modules.EnableJsonConverterWithStandardTools;

@ContainerModule
@Context(Constants.Contexts.DEFAULT_CONTEXT)
@PackagesScan(
        @PackageScan("com.jgames.survival.model.game.modules")
)
@EnableJsonConverterWithStandardTools
public class MainModule extends AnnotationModule {
}
