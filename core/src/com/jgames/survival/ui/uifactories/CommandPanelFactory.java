package com.jgames.survival.ui.uifactories;

import java.util.List;

import ru.jengine.utils.CollectionUtils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.uiscriptelements.commandpanel.CommandAndCellState;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitButtonClick;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WaitMapCell;
import com.jgames.survival.ui.uiscriptelements.commandpanel.WithPatternPrinter;
import com.jgames.survival.ui.widgets.CommandButton;
import com.jgames.survival.ui.widgets.CommandButton.ClickOnCommandButton;
import com.jgames.survival.ui.widgets.TextListWidget;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;

/**
 * Фабрика, собирающая панель с кнопками команд в пользовательском интерфейсе
 */
public class CommandPanelFactory implements UIFactory { //TODO добавить возможность изменения команд
    private static final String PATTERN = "Game object id = {%s}";
    private static final String ID_COMMAND_NAME = "Id";

    private final ClickOnCommandButton commandCallback;

    private Table commandPanel;

    public CommandPanelFactory(ClickOnCommandButton commandCallback) {
        this.commandCallback = commandCallback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureRegion region = uiElements.getTextureStorage().createSprite(Constants.BUTTON_BACKGROUND);
        commandPanel = new Table();

        MapFillingPresenter mapFillingPresenter = uiElements.getPresentingGameState().getModulePresenter(MapFillingModule.NAME);

        TextListWidget textInformation = uiElements.getWidget("infoPanel", TextListWidget.class);

        CommandButton button = new CommandButton(ID_COMMAND_NAME, region, commandCallback);

        uiElements.getScriptMachine().registerScript(
                new CyclicUIScript<>("objectIdExtractor", new CommandAndCellState(),
                        new WaitButtonClick(button),
                        new WaitMapCell(),
                        new WithPatternPrinter(PATTERN, textInformation, state -> {
                            List<Integer> idsOnCell = mapFillingPresenter.getIdsOnCell(state.getMapCell().getCoordinateAsPoint());
                            return idsOnCell.isEmpty() ? "None" : CollectionUtils.getLast(idsOnCell).toString();
                        })
                ));

        commandPanel.add(button).pad(10f);

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
