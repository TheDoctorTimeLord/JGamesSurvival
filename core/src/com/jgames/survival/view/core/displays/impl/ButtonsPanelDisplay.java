package com.jgames.survival.view.core.displays.impl;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.view.core.CanBeActor;
import com.jgames.survival.view.core.UIEventBus;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.uielements.impl.ActorWrapperElement;
import com.jgames.survival.view.core.uielements.impl.button.ButtonActorWrapperElement;

public class ButtonsPanelDisplay extends BaseDisplay {
    private final Table buttonsTable;
    private final List<ButtonActorWrapperElement> buttons = new ArrayList<>();

    public ButtonsPanelDisplay(String displayName, List<Button> buttons, ButtonsAdditionalParameters parameters) {
        super(displayName);
        this.buttonsTable = new Table();

        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            Cell<Button> buttonCell = buttonsTable.add(button)
                    .width(button.getWidth())
                    .height(button.getHeight());

            if (parameters.vertical) {
                buttonsTable.row();
            }
            if (i != buttons.size() - 1) {
                if (parameters.vertical) {
                    buttonCell.padBottom(parameters.buttonPadding);
                } else {
                    buttonCell.padLeft(parameters.buttonPadding);
                }
            }

            this.buttons.add(new ButtonActorWrapperElement(button));
        }
        buttonsTable.pad(parameters.buttonPadding);

        buttonsTable.setSize(buttonsTable.getPrefWidth(), buttonsTable.getPrefHeight());
    }

    @Override
    public void onBind(UIEventBus eventBus, @Nullable Display parent) {
        super.onBind(eventBus, parent);
        for (ButtonActorWrapperElement button : buttons) {
            button.onBind(eventBus, this);
        }
    }

    @Override
    public void onUnbind(UIEventBus eventBus) {
        super.onUnbind(eventBus);
        for (ButtonActorWrapperElement button : buttons) {
            button.onUnbind(eventBus);
        }
    }

    @Override
    public Table asActor() {
        return buttonsTable;
    }

    @Override
    public CanBeActor findNamedElement(String findingElementName) {
        for (ActorWrapperElement button : buttons) {
            if (button.getName().equals(findingElementName)) {
                return button;
            }
        }
        return null;
    }

    public static class ButtonsAdditionalParameters {
        private float buttonPadding = 0;
        private boolean vertical = false;

        public ButtonsAdditionalParameters buttonPadding(float buttonPadding) {
            this.buttonPadding = buttonPadding;
            return this;
        }

        public ButtonsAdditionalParameters vertical(boolean vertical) {
            this.vertical = vertical;
            return this;
        }
    }
}
