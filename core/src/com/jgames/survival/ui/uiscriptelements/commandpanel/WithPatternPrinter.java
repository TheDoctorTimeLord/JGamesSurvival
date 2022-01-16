package com.jgames.survival.ui.uiscriptelements.commandpanel;

import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.widgets.TextListWidget;

public class WithPatternPrinter implements UIRunnableScript<CommandAndCellState> {
    private final String pattern;
    private final TextListWidget textInformation;

    public WithPatternPrinter(String pattern, TextListWidget textInformation) {
        this.pattern = pattern;
        this.textInformation = textInformation;
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        textInformation.addText(String.format(pattern, state.getMapCell().getRow(), state.getMapCell().getColumn()));
    }
}
