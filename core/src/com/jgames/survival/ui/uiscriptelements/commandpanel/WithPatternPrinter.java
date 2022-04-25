package com.jgames.survival.ui.uiscriptelements.commandpanel;

import java.util.function.Function;

import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.contextes.UIScriptElementContext;
import com.jgames.survival.ui.widgets.TextListWidget;

public class WithPatternPrinter implements UIRunnableScript<CommandAndCellState> {
    private final String pattern;
    private final TextListWidget textInformation;
    private final Function<CommandAndCellState, String> dataExtractor;

    public WithPatternPrinter(String pattern, TextListWidget textInformation,
            Function<CommandAndCellState, String> dataExtractor) {
        this.pattern = pattern;
        this.textInformation = textInformation;
        this.dataExtractor = dataExtractor;
    }

    @Override
    public void handle(UIScriptElementContext context, CommandAndCellState state) {
        textInformation.addText(String.format(pattern, dataExtractor.apply(state)));
    }
}
