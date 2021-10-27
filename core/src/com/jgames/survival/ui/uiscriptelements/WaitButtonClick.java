package com.jgames.survival.ui.uiscriptelements;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.actions.CommandButtonClicked;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.clickable.CommandButton;

public class WaitButtonClick implements UIScriptElement<CommandAndCellState> {
    private final CommandButton commandButton;

    public WaitButtonClick(CommandButton commandButton) {
        this.commandButton = commandButton;
    }

    @Override
    public boolean needWait() {
        return true;
    }

    @Override
    public boolean isValid(UIAction action) {
        return action instanceof CommandButtonClicked
                && commandButton.equals(((CommandButtonClicked)action).getCommandButton());
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        CommandButtonClicked commandButtonClicked = (CommandButtonClicked)context.getDispatchedAction();
        if (commandButton.equals(commandButtonClicked.getCommandButton())) {
            state.setCommandButton(commandButton);
        } else {
            context.resetActiveScript();
        }
    }
}
