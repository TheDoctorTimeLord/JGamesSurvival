package com.jgames.survival.control.clickhandlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.control.UIActionDispatcher;
import com.jgames.survival.control.actions.MapCellClicked;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.ui.widgets.MapCell.ClickOnMapCell;

public class MapCellClickHandler implements ClickOnMapCell {
    private final UIActionDispatcher actionDispatcher;

    public MapCellClickHandler(UIActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void clicked(InputEvent event, MapCell mapCell) {
        MapCellClicked action = Pools.obtain(MapCellClicked.class);
        action.setClickedCell(mapCell);

        actionDispatcher.dispatch(action, mapCellAction -> Pools.free(action));
    }
}
