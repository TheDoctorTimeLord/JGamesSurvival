package com.jgames.survival.presenter.filling.clickactions;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.ui.widgets.MapCell;

public class MapCellClicked implements UIAction, Poolable {
    private MapCell clickedCell;

    @Override
    public void reset() {
        this.clickedCell = null;
    }

    public MapCell getClickedCell() {
        return clickedCell;
    }

    public void setClickedCell(MapCell clickedCell) {
        this.clickedCell = clickedCell;
    }
}
