package com.jgames.survival.control.clickhandlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Pools;
import com.jgames.survival.control.UIActionDispatcher;
import com.jgames.survival.control.actions.CommandButtonClicked;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.CommandButton.ClickOnCommandButton;

public class CommandButtonClickHandler implements ClickOnCommandButton {
    private final UIActionDispatcher actionDispatcher;

    public CommandButtonClickHandler(UIActionDispatcher actionDispatcher) {
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    public void clicked(InputEvent event, CommandButton commandButton) {
        CommandButtonClicked action = Pools.obtain(CommandButtonClicked.class);
        action.setCommandButton(commandButton);

        actionDispatcher.dispatch(action, Pools::free);
    }
}
