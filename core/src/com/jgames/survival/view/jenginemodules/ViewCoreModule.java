package com.jgames.survival.view.jenginemodules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule
@Context(CommonConstants.VIEW_CONTEXT)
@PackageScan("com.jgames.survival.view.core")
public class ViewCoreModule extends AnnotationModule {
}