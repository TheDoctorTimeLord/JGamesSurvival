package com.jgames.survival.ui.uiscriptelements.commandpanel;

import com.jgames.survival.control.uiscripts.UIScriptState;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.MapCell;

public class CommandAndCellState implements UIScriptState {
    private CommandButton commandButton;
    private MapCell mapCell;

    @Override
    public void reset() {
        this.commandButton = null;
        this.mapCell = null;
    }

    public CommandButton getCommandButton() {
        return commandButton;
    }

    public MapCell getMapCell() {
        return mapCell;
    }

    public void setCommandButton(CommandButton commandButton) {
        this.commandButton = commandButton;
    }

    public void setMapCell(MapCell mapCell) {
        this.mapCell = mapCell;
    }
}
