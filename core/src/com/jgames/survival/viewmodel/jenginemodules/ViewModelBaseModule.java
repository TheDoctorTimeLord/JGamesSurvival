package com.jgames.survival.viewmodel.jenginemodules;

import ru.jengine.beancontainer.Constants.Contexts;
import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.ImportModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.annotations.PackagesScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.jsonconverter.modules.EnableJsonConverterWithStandardTools;

import com.jgames.survival.utils.CommonConstants;
import com.jgames.survival.view.jenginemodules.ViewBaseModule;

@ContainerModule(contextName = CommonConstants.CLIENT_CONTEXT, beanSources = Contexts.DEFAULT_CONTEXT)
@PackagesScan({
        @PackageScan("com.jgames.survival.viewmodel.jenginemodules"),
        @PackageScan("com.jgames.survival.utils.eventbus")
})
@ImportModule(ViewBaseModule.class)
@EnableJsonConverterWithStandardTools
public class ViewModelBaseModule extends AnnotationModule {
}
