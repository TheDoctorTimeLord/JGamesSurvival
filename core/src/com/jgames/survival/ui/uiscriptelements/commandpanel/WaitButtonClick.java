package com.jgames.survival.ui.uiscriptelements.commandpanel;

import java.util.Collections;
import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.actions.CommandButtonClicked;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.widgets.CommandButton;

public class WaitButtonClick implements UIScriptElement<CommandAndCellState> {
    private final CommandButton commandButton;

    public WaitButtonClick(CommandButton commandButton) {
        this.commandButton = commandButton;
    }

    @Override
    public boolean isRunnableElement() {
        return false;
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof CommandButtonClicked
                && commandButton.equals(((CommandButtonClicked)action).getCommandButton());
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return Collections.singleton(CommandButtonClicked.class);
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        state.setCommandButton(commandButton);
    }

    @Override
    public boolean rollback(UIAction action, CommandAndCellState state) {
        if (action instanceof CommandButtonClicked && commandButton.equals(((CommandButtonClicked)action).getCommandButton())) {
            return false;
        }

        state.setCommandButton(null);
        return true;
    }
}
