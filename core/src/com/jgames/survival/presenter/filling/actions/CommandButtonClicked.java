package com.jgames.survival.presenter.filling.actions;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jgames.survival.presenter.core.UIAction;
import com.jgames.survival.ui.widgets.CommandButton;

/**
 * Действие пользовательского интерфейса, уведомляющее о том, что была нажата кнопка
 * {@link com.jgames.survival.ui.uifactories.CommandPanelFactory команды}
 */
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
