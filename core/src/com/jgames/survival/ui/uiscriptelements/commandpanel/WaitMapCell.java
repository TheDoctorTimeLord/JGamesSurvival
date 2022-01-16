package com.jgames.survival.ui.uiscriptelements.commandpanel;

import java.util.Collections;
import java.util.Set;

import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.presenter.filling.actions.MapCellClicked;
import com.jgames.survival.presenter.core.uiscripts.UIWaitedScriptElement;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;

public class WaitMapCell implements UIWaitedScriptElement<CommandAndCellState> {
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
