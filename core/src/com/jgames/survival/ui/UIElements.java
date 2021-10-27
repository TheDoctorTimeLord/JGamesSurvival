package com.jgames.survival.ui;

import com.jgames.survival.control.uiscripts.UIScriptMachine;

public class UIElements {
    private final UIScriptMachine scriptMachine;
    private final TextInformation textInformation;

    public UIElements(UIScriptMachine scriptMachine, TextInformation textInformation) {
        this.scriptMachine = scriptMachine;
        this.textInformation = textInformation;
    }

    public UIScriptMachine getScriptMachine() {
        return scriptMachine;
    }

    public TextInformation getTextInformation() {
        return textInformation;
    }
}
