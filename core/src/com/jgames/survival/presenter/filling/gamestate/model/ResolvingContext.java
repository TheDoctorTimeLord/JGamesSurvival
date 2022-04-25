package com.jgames.survival.presenter.filling.gamestate.model;

/**
 * Описание объекта, который резолвится.
 */
public class ResolvingContext {
    private final String objectTypeName;
    private final DrawingContext drawingContext;

    public ResolvingContext(String objectTypeName, DrawingContext drawingContext) {
        this.objectTypeName = objectTypeName;
        this.drawingContext = drawingContext;
    }

    public DrawingContext getDrawingContext() {
        return drawingContext;
    }

    public String getObjectTypeName() {
        return objectTypeName;
    }
}
