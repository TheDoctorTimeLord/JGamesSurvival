package com.jgames.survival.ui.uiscriptelements.commandpanel;

import com.jgames.survival.presenter.core.uiscripts.UIScriptState;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.MapCell;

/**
 * Состояние скрипта обработки {@link com.jgames.survival.ui.uifactories.CommandPanelFactory комманд} содержит
 * выбранную команду и нажатую ячейку на игровом поле
 */
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
