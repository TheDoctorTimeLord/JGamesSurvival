package com.jgames.survival.ui.uiscriptelements.commandpanel;

import java.util.Set;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.widgets.TextListWidget;

public class WithPatternPrinter implements UIScriptElement<CommandAndCellState> {
    private final String pattern;
    private final TextListWidget textInformation;

    public WithPatternPrinter(String pattern, TextListWidget textInformation) {
        this.pattern = pattern;
        this.textInformation = textInformation;
    }

    @Override
    public boolean isRunnableElement() {
        return true;
    }

    @Override
    public boolean isValid(UIAction action) {
        return true;
    }

    @Override
    public Set<Class<? extends UIAction>> getWaitedActions() {
        return null;
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        textInformation.addText(String.format(pattern, state.getMapCell().getRow(), state.getMapCell().getColumn()));
    }

    @Override
    public boolean rollback(UIAction action, CommandAndCellState state) {
        return true;
    }
}
