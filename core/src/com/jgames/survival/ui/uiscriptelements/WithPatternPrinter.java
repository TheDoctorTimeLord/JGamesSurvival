package com.jgames.survival.ui.uiscriptelements;

import com.jgames.survival.control.UIAction;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.control.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.TextInformation;

public class WithPatternPrinter implements UIScriptElement<CommandAndCellState> {
    private final String pattern;
    private final TextInformation textInformation;

    public WithPatternPrinter(String pattern, TextInformation textInformation) {
        this.pattern = pattern;
        this.textInformation = textInformation;
    }

    @Override
    public boolean needWait() {
        return false;
    }

    @Override
    public boolean isValid(UIAction action) {
        return true;
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        textInformation.addText(String.format(pattern, state.getMapCell().getRow(), state.getMapCell().getColumn()));
    }
}
