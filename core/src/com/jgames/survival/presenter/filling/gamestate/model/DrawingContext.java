package com.jgames.survival.presenter.filling.gamestate.model;

import javax.annotation.Nullable;

/**
 * Метаданные отрисуемого объекта.
 */
public class DrawingContext {
    private ModelData modelData;

    @Nullable
    public ModelData getModelData() {
        return modelData;
    }

    public void setModelData(ModelData modelData) {
        this.modelData = modelData;
    }
}
