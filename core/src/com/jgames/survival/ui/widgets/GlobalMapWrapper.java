package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GlobalMapWrapper<C extends Actor> extends Actor {
    public static final String GLOBAL_MAP_NAME = "globalMap";
    private final Table wrapped;

    public GlobalMapWrapper(Table wrapped) {
        this.wrapped = wrapped;
    }

    public Table getWrapped() {
        return wrapped;
    }

    @Override
    public String getName() {
        return GLOBAL_MAP_NAME;
    }

    public C getTableCell(int x, int y) {
        if (x < 0 || wrapped.getColumns() <= x) {
            throw new IllegalArgumentException("Column must be in [0; " + wrapped.getColumns() + ")");
        }
        if (y < 0 || wrapped.getRows() <= y) {
            throw new IllegalArgumentException("Row must be in [0; " + wrapped.getRows() + ")");
        }

        int index = (wrapped.getRows() - y - 1) * wrapped.getColumns() + x;
        return (C)wrapped.getCells().get(index).getActor();
    }
}
