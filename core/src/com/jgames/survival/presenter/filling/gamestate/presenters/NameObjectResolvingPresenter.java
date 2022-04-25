package com.jgames.survival.presenter.filling.gamestate.presenters;

import com.jgames.survival.presenter.core.gamestate.ModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.model.ResolvingContext;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelData;

import java.util.Collection;

/**
 * Резолвит объекты в имена и метаданные объектов, которым имена соответствуют.
 */
public interface NameObjectResolvingPresenter extends ModulePresenter {
    /**
     * Разрезолвить объекты в имена и метаданные объектов, которым имена соответствуют.
     */
    Collection<ResolvingContext> resolveModelData(Collection<ModelData> modelDataCollection);
}
