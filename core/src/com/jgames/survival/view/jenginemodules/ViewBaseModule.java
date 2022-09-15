package com.jgames.survival.view.jenginemodules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.jsonconverter.modules.EnableJsonConverterWithStandardTools;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule
@Context(value = CommonConstants.VIEW_CONTEXT, beanSources = CommonConstants.VIEW_MODEL_CONTEXT)
@PackageScan("com.jgames.survival.view.jenginemodules")
@EnableJsonConverterWithStandardTools
public class ViewBaseModule extends AnnotationModule {
}
