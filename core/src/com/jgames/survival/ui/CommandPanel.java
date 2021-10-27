package com.jgames.survival.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jgames.survival.control.uiscripts.UIScript;
import com.jgames.survival.control.uiscripts.UIScriptElement;
import com.jgames.survival.ui.clickable.CommandButton;
import com.jgames.survival.ui.clickable.CommandButton.ClickOnCommandButton;
import com.jgames.survival.ui.uiscriptelements.CommandAndCellState;
import com.jgames.survival.ui.uiscriptelements.WaitButtonClick;
import com.jgames.survival.ui.uiscriptelements.WaitMapCell;
import com.jgames.survival.ui.uiscriptelements.WithPatternPrinter;

public class CommandPanel extends Table { //TODO сделать команды нормально
    public CommandPanel(UIElements uiElements, ClickOnCommandButton commandCallback) {
        Texture texture = new Texture("cell.png"); //TODO централизовать работу с изображениями
        TextureRegion region = new TextureRegion(texture);

        String[] patterns = {
                "From command 1. Cell {x=%d, y=%d}",
                "From command 2.... Cell {x=%d, y=%d}",
                "From command 3....... Cell {x=%d, y=%d}"
        };

        for (int i = 0; i < 3; i++) {
            CommandButton button = new CommandButton(String.valueOf(i + 1), region, commandCallback);

            List<UIScriptElement<CommandAndCellState>> scriptElements = new ArrayList<>();
            scriptElements.add(new WaitButtonClick(button));
            scriptElements.add(new WaitMapCell());
            scriptElements.add(new WithPatternPrinter(patterns[i], uiElements.getTextInformation()));
            uiElements.getScriptMachine().registerScript(new UIScript<>(scriptElements, new CommandAndCellState()));

            add(button).pad(10f);
        }

        row();
    }
}
