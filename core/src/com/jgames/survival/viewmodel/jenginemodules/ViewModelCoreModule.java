package com.jgames.survival.viewmodel.jenginemodules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule(contextName = CommonConstants.VIEW_MODEL_CONTEXT)
@PackageScan("com.jgames.survival.viewmodel.core")
public class ViewModelCoreModule extends AnnotationModule {
}
