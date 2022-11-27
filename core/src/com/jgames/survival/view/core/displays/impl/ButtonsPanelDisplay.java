package com.jgames.survival.view.core.displays.impl;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.view.core.displays.Display;

public class ButtonsPanelDisplay implements Display {
    private final Table buttonsTable;

    public ButtonsPanelDisplay(List<Button> buttons, boolean vertical, ButtonsAdditionalParameters parameters) {
        this.buttonsTable = new Table();

        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            Cell<Button> buttonCell = buttonsTable.add(button)
                    .width(button.getWidth())
                    .height(button.getHeight());

            if (vertical) {
                buttonsTable.row();
            }
            if (i != buttons.size() - 1) {
                if (vertical) {
                    buttonCell.padBottom(parameters.buttonPadding);
                } else {
                    buttonCell.padLeft(parameters.buttonPadding);
                }
            }
        }
        buttonsTable.pad(parameters.buttonPadding);

        buttonsTable.validate();
    }

    @Override
    public Table asActor() {
        return buttonsTable;
    }

    @Override
    public Actor findNamedElement(String findingElementName) {
        for (Cell<?> cell : asActor().getCells()) {
            Actor element = cell.getActor();
            String elementName = element.getName();
            if (elementName != null && elementName.equals(findingElementName)) {
                return element;
            }
        }
        return null;
    }

    public static class ButtonsAdditionalParameters {
        private float buttonPadding = 0;

        public ButtonsAdditionalParameters buttonPadding(float buttonPadding) {
            this.buttonPadding = buttonPadding;
            return this;
        }
    }
}
