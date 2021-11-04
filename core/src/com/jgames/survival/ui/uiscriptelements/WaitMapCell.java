package com.jgames.survival.ui.uiscriptelements;

import java.util.Collections;
import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.actions.MapCellClicked;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;

public class WaitMapCell implements UIScriptElement<CommandAndCellState> {
    @Override
    public boolean isRunnableElement() {
        return false;
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof MapCellClicked;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return Collections.singleton(MapCellClicked.class);
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        MapCellClicked mapCellClicked = (MapCellClicked)context.getDispatchedAction();
        state.setMapCell(mapCellClicked.getClickedCell());
    }

    @Override
    public boolean rollback(UIAction action, CommandAndCellState state) {
        state.setMapCell(null);
        return true;
    }
}
