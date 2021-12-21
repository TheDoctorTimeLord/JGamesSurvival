package com.jgames.survival.ui.uicomponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.control.uiscripts.sctipts.UIScriptBuilder;
import com.jgames.survival.ui.UIComponent;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.uiscriptelements.commandpanel.CommandAndCellState;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitButtonClick;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitMapCell;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WithPatternPrinter;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.CommandButton.ClickOnCommandButton;
import com.jgames.survival.ui.widgets.TextListWidget;

public class CommandPanelComponent implements UIComponent { //TODO добавить возможность изменения команд
    private final ClickOnCommandButton commandCallback;
    private final TextureRegion region;

    private Table commandPanel;

    public CommandPanelComponent(ClickOnCommandButton commandCallback, Texture commandBackground) {
        this.commandCallback = commandCallback;
        this.region = new TextureRegion(commandBackground);
    }

    @Override
    public void prepareComponent(UIElements uiElements) {//TODO централизовать работу с изображениями
        commandPanel = new Table();

        TextListWidget textInformation = uiElements.getWidget("infoPanel", TextListWidget.class);

        String[] patterns = {
                "From command 1. Cell {x=%d, y=%d}",
                "From command 2.... Cell {x=%d, y=%d}",
                "From command 3....... Cell {x=%d, y=%d}"
        };

        for (int i = 0; i < 3; i++) {
            CommandButton button = new CommandButton(String.valueOf(i + 1), region, commandCallback);

            uiElements.getScriptMachine().registerScript(
                    UIScriptBuilder.builder("button" + i, new CommandAndCellState())
                            .addScriptElement(new WaitButtonClick(button))
                            .addScriptElement(new WaitMapCell())
                            .addScriptElement(new WithPatternPrinter(patterns[i], textInformation))
                            .buildCyclic());

            commandPanel.add(button).pad(10f);
        }

        commandPanel.align(Align.center | Align.bottom);
        commandPanel.setFillParent(true);
    }

    @Override
    public Actor getFrontWidget() {
        return commandPanel;
    }

    @Override
    public Actor getActionableComponent() {
        return null;
    }
}
