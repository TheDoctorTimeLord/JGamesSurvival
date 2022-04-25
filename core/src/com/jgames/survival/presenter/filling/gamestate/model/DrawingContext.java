package com.jgames.survival.presenter.filling.gamestate.model;

import com.jgames.survival.presenter.filling.gamestate.modules.ModelData;

/**
 * Метаданные отрисуемого объекта.
 */
public class DrawingContext {
    private ModelData modelData;

    public ModelData getModelData() {
        return modelData;
    }

    public void setModelData(ModelData modelData) {
        this.modelData = modelData;
    }
}
