package com.jgames.survival.ui.uiscriptelements;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.actions.MapCellClicked;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public class WaitMapCell implements UIScriptElement<CommandAndCellState> {
    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof MapCellClicked;
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        MapCellClicked mapCellClicked = (MapCellClicked)context.getDispatchedAction();
        state.setMapCell(mapCellClicked.getClickedCell());
    }
}
