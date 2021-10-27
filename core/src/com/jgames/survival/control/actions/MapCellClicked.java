package com.jgames.survival.control.actions;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.ui.clickable.MapCell;

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
