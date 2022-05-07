package com.jgames.survival.presenter.modules;

import ru.jengine.beancontainer.annotations.ContainerModule;
import ru.jengine.beancontainer.annotations.Context;
import ru.jengine.beancontainer.annotations.PackageScan;
import ru.jengine.beancontainer.implementation.moduleimpls.AnnotationModule;
import ru.jengine.jsonconverter.modules.EnableJsonConverterWithStandardTools;

import com.jgames.survival.utils.CommonConstants;

@ContainerModule
@Context(CommonConstants.GAME_PRESENTER_AND_UI)
@PackageScan("com.jgames.survival.presenter.modules")
@EnableJsonConverterWithStandardTools
public class PresenterAndUIMainModule extends AnnotationModule {
}
