package com.jgames.survival.ui.uifactories;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.clickactions.ButtonClickedHandler;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.GameObjectsModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.presenters.DrawingModulePresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.GameObjectsPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.MapFillingPresenter;
import com.jgames.survival.presenter.filling.gamestate.presenters.NameObjectResolvingPresenter;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.assets.TextureStorage;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMap;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.ApplyPhaseChanges;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.CallUpdateGame;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandlePhaseButton;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandleTurnButton;
import com.jgames.survival.ui.uiscriptelements.common.WaitButtonClicked;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.utils.WidgetUtils;

public class PhaseAndTurnPanelFactory implements UIFactory {
    private static final int BUTTON_MIDDLE_HEIGHT = 50;
    private static final int BUTTON_MIDDLE_WIDTH = 120;

    private final ButtonClickedHandler buttonCallback;
    private NinePatch buttonsBackground;

    private Table panel;

    public PhaseAndTurnPanelFactory(ButtonClickedHandler callback) {
        this.buttonCallback = callback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureStorage storage = uiElements.getTextureStorage();

        buttonsBackground = storage.createNinePatch(Constants.BUTTON_BACKGROUND);
        buttonsBackground.setMiddleHeight(BUTTON_MIDDLE_HEIGHT);
        buttonsBackground.setMiddleWidth(BUTTON_MIDDLE_WIDTH);

        TextButton nextPhaseButton = WidgetUtils.createButton("Next Phase", buttonsBackground, buttonCallback);
        TextButton nextTurnButton = WidgetUtils.createButton("Next Turn", buttonsBackground, buttonCallback);

        nextPhaseButton.setDisabled(true);

        GlobalMapWrapper<MapCell> globalMap = uiElements.getWidget(GlobalMapWrapper.GLOBAL_MAP_NAME, GlobalMapWrapper.class);

        PresentingGameState presentingGameState = uiElements.getPresentingGameState();
        MapFillingPresenter mapFillingPresenter = presentingGameState.getModulePresenter(MapFillingModule.NAME);
        GameObjectsPresenter gameObjectsPresenter = presentingGameState.getModulePresenter(GameObjectsModule.NAME);
        NameObjectResolvingPresenter nameObjectResolvingPresenter = presentingGameState.getModulePresenter(NameObjectResolvingModule.NAME);
        DrawingModulePresenter drawingModulePresenter = presentingGameState.getModulePresenter(DrawingModule.NAME);

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleTurnButton", new EmptyScriptState(),
                new WaitButtonClicked(nextTurnButton),
                new CallUpdateGame(uiElements.getActionSender()),
                new EndHandleTurnButton(nextTurnButton, nextPhaseButton))
        );

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handlePhaseButton", new EmptyScriptState(),
                new WaitButtonClicked(nextPhaseButton),
                new ApplyPhaseChanges(uiElements.getPresentingGameState()),
                new UpdateMap(globalMap, mapFillingPresenter, gameObjectsPresenter, nameObjectResolvingPresenter, drawingModulePresenter),
                new EndHandlePhaseButton(uiElements.getPresentingGameState(), nextTurnButton, nextPhaseButton)
        ));

        panel = WidgetUtils.createButtonPanel(Align.bottomRight, nextPhaseButton, nextTurnButton);
    }

    @Override
    public Actor getFrontWidget() {
        return panel;
    }

    @Override
    public Actor getActionableComponent() {
        return null;
    }
}
