package com.jgames.survival.ui.uifactories;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
import com.jgames.survival.ui.uifactories.config.SaveAndLoadConfig;
import com.jgames.survival.ui.uifactories.config.UIFactoryConfig;
import com.jgames.survival.ui.uiscriptelements.common.ChangeDisabledButtonState;
import com.jgames.survival.ui.uiscriptelements.common.WaitButtonClicked;
import com.jgames.survival.utils.WidgetUtils;

public class SaveAndLoadPanelFactory implements UIFactory {
    private final File saveFile;

    private final SaveAndLoadConfig saveAndLoadConfig;
    private final ButtonClickedHandler buttonCallback;

    private Table panel;

    public SaveAndLoadPanelFactory(UIFactoryConfig config, ButtonClickedHandler buttonCallback) {
        this.buttonCallback = buttonCallback;

        this.saveAndLoadConfig = config.getSaveAndLoad();
        this.saveFile = Gdx.files.internal(saveAndLoadConfig.getSaveFileName()).file();
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureStorage storage = uiElements.getTextureStorage();

        NinePatch buttonsBackground = storage.createNinePatch(Constants.BUTTON_BACKGROUND);
        buttonsBackground.setMiddleHeight(saveAndLoadConfig.getButtonMiddleHeight());
        buttonsBackground.setMiddleWidth(saveAndLoadConfig.getButtonMiddleWidth());

        TextButton saveButton = WidgetUtils.createButton(saveAndLoadConfig.getSaveName(), buttonsBackground, buttonCallback);
        TextButton loadButton = WidgetUtils.createButton(saveAndLoadConfig.getLoadName(), buttonsBackground, buttonCallback);

        GameActionSender actionSender = uiElements.getActionSender();

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleSaveButton", new EmptyScriptState(),
                new WaitButtonClicked(saveButton),
                new ChangeDisabledButtonState(saveButton),
                (UIRunnableScript<EmptyScriptState>)(context, state) -> actionSender.sendGameAction(new SaveBattleAction(
                        saveFile)),
                new ChangeDisabledButtonState(saveButton)
        ));

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleLoadButton", new EmptyScriptState(),
                new WaitButtonClicked(loadButton),
                new ChangeDisabledButtonState(loadButton),
                (UIRunnableScript<EmptyScriptState>)(context, state) -> actionSender.sendGameAction(new LoadBattleAction(
                        saveFile)),
                new ChangeDisabledButtonState(loadButton)
        ));

        panel = WidgetUtils.createButtonPanel(saveAndLoadConfig.getAlign(), saveButton, loadButton);
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
