package com.jgames.survival.viewmodel.jenginemodules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule(contextName = CommonConstants.CLIENT_CONTEXT)
@PackageScan("com.jgames.survival.viewmodel.impl")
public class ViewModelImplementationModule extends AnnotationModule {
}
