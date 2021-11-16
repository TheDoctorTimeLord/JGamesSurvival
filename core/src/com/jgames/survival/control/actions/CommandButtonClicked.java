package com.jgames.survival.control.actions;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.control.UIAction;
import com.jgames.survival.ui.widgets.CommandButton;

public class CommandButtonClicked implements UIAction, Poolable {
    private CommandButton commandButton;

    @Override
    public void reset() {
        this.commandButton = null;
    }

    public CommandButton getCommandButton() {
        return commandButton;
    }

    public void setCommandButton(CommandButton commandButton) {
        this.commandButton = commandButton;
    }
}
