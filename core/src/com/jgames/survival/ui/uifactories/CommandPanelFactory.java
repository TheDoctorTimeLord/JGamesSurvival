package com.jgames.survival.ui.uifactories;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.uiscriptelements.commandpanel.CommandAndCellState;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitButtonClick;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitMapCell;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WithPatternPrinter;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.CommandButton.ClickOnCommandButton;
import com.jgames.survival.ui.widgets.TextListWidget;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;

/**
 * Фабрика, собирающая панель с кнопками команд в пользовательском интерфейсе
 */
public class CommandPanelFactory implements UIFactory { //TODO добавить возможность изменения команд
    private final ClickOnCommandButton commandCallback;

    private Table commandPanel;

    public CommandPanelFactory(ClickOnCommandButton commandCallback) {
        this.commandCallback = commandCallback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureRegion region = uiElements.getTextureStorage().createSprite(Constants.COMMON);
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
                    new CyclicUIScript<>("button" + i, new CommandAndCellState(),
                            new WaitButtonClick(button),
                            new WaitMapCell(),
                            new WithPatternPrinter(patterns[i], textInformation)
                    ));

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
