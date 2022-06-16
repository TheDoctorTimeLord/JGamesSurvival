package com.jgames.survival.ui.uifactories;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.model.api.interaction.actions.LoadBattleAction;
import com.jgames.survival.model.api.interaction.actions.SaveBattleAction;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.UIRunnableScript;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.clickactions.ButtonClickedHandler;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.assets.TextureStorage;
import com.jgames.survival.ui.uiscriptelements.common.ChangeDisabledButtonState;
import com.jgames.survival.ui.uiscriptelements.common.WaitButtonClicked;
import com.jgames.survival.utils.WidgetUtils;

public class SaveAndLoadPanelFactory implements UIFactory {
    private static final int BUTTON_MIDDLE_HEIGHT = 50;
    private static final int BUTTON_MIDDLE_WIDTH = 120;
    private static final File SAVE_FILE = Gdx.files.internal("save.json").file();

    private final ButtonClickedHandler buttonCallback;

    private Table panel;

    public SaveAndLoadPanelFactory(ButtonClickedHandler buttonCallback) {
        this.buttonCallback = buttonCallback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureStorage storage = uiElements.getTextureStorage();

        NinePatch buttonsBackground = storage.createNinePatch(Constants.BUTTON_BACKGROUND);
        buttonsBackground.setMiddleHeight(BUTTON_MIDDLE_HEIGHT);
        buttonsBackground.setMiddleWidth(BUTTON_MIDDLE_WIDTH);

        TextButton saveButton = WidgetUtils.createButton("Save", buttonsBackground, buttonCallback);
        TextButton loadButton = WidgetUtils.createButton("Load", buttonsBackground, buttonCallback);

        GameActionSender actionSender = uiElements.getActionSender();

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleSaveButton", new EmptyScriptState(),
                new WaitButtonClicked(saveButton),
                new ChangeDisabledButtonState(saveButton),
                (UIRunnableScript<EmptyScriptState>)(context, state) -> actionSender.sendGameAction(new SaveBattleAction(SAVE_FILE)),
                new ChangeDisabledButtonState(saveButton)
        ));

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleLoadButton", new EmptyScriptState(),
                new WaitButtonClicked(loadButton),
                new ChangeDisabledButtonState(loadButton),
                (UIRunnableScript<EmptyScriptState>)(context, state) -> actionSender.sendGameAction(new LoadBattleAction(SAVE_FILE)),
                new ChangeDisabledButtonState(loadButton)
        ));

        panel = WidgetUtils.createButtonPanel(Align.topRight, saveButton, loadButton);
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
