package com.jgames.survival.viewmodel.jenginemodules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.jsonconverter.modules.EnableJsonConverterWithStandardTools;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule
@Context(CommonConstants.VIEW_MODEL_CONTEXT)
@PackageScan("com.jgames.survival.viewmodel.jenginemodules")
@EnableJsonConverterWithStandardTools
public class ViewModelBaseModule extends AnnotationModule {
}
