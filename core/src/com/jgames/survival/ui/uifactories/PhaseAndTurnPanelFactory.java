package com.jgames.survival.ui.uifactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.presenter.core.uiscripts.EmptyScriptState;
import com.jgames.survival.presenter.core.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.presenter.filling.clickhandlers.PhaseOrTurnClickedHandler;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.ApplyPhaseChanges;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.CallUpdateGame;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandlePhaseButton;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandleTurnButton;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.WaitPhaseOrTurnClicked;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;
import com.jgames.survival.utils.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.utils.assets.TextureStorage;

public class PhaseAndTurnPanelFactory implements UIFactory {
    private static final int BUTTON_MIDDLE_HEIGHT = 50;
    private static final int BUTTON_MIDDLE_WIDTH = 120;

    private final PhaseOrTurnClickedHandler buttonCallback;
    private NinePatch buttonsBackground;

    private Table panel;

    public PhaseAndTurnPanelFactory(PhaseOrTurnClickedHandler callback) {
        this.buttonCallback = callback;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        TextureStorage storage = uiElements.getTextureStorage();

        buttonsBackground = storage.createNinePatch(Constants.COMMON);
        buttonsBackground.setMiddleHeight(BUTTON_MIDDLE_HEIGHT);
        buttonsBackground.setMiddleWidth(BUTTON_MIDDLE_WIDTH);

        TextureRegion[] directedPersonTextures = new TextureRegion[] {
                storage.createSprite(Constants.PERSON_UP),
                storage.createSprite(Constants.PERSON_RIGHT),
                storage.createSprite(Constants.PERSON_DOWN),
                storage.createSprite(Constants.PERSON_LEFT)
        };

        TextButton nextPhaseButton = createButton("Next Phase");
        TextButton nextTurnButton = createButton("Next Turn");

        nextPhaseButton.setDisabled(true);

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handleTurnButton", new EmptyScriptState(),
                new WaitPhaseOrTurnClicked(nextTurnButton),
                new CallUpdateGame(uiElements.getActionSender()),
                new EndHandleTurnButton(nextTurnButton, nextPhaseButton))
        );

        GlobalMapWrapper<MapCell> globalMap = uiElements.getWidget(GlobalMapWrapper.GLOBAL_MAP_NAME, GlobalMapWrapper.class);

        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>("handlePhaseButton", new EmptyScriptState(),
                new WaitPhaseOrTurnClicked(nextPhaseButton),
                new ApplyPhaseChanges(uiElements.getPresentingGameState(), globalMap, directedPersonTextures),
                new EndHandlePhaseButton(uiElements.getPresentingGameState(), nextTurnButton, nextPhaseButton)
        ));

        createButtonPanel(nextPhaseButton, nextTurnButton);
    }

    private TextButton createButton(String buttonText) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = new NinePatchDrawable(buttonsBackground);
        textButtonStyle.down = new NinePatchDrawable(buttonsBackground).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
        textButtonStyle.disabled = new NinePatchDrawable(buttonsBackground).tint(new Color(0.6f, 0.6f, 0.6f, 1f));
        textButtonStyle.disabledFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = new Color(0, 0, 0, 1);
        TextButton button = new TextButton(buttonText, textButtonStyle);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!button.isDisabled()) buttonCallback.clicked(event, x, y);
            }
        });
        return button;
    }

    private void createButtonPanel(TextButton nextPhaseButton, TextButton nextTurnButton) {
        panel = new Table();
        panel.add(nextPhaseButton).pad(5).row();
        panel.add(nextTurnButton).padBottom(5);
        panel.setFillParent(true);
        panel.align(Align.bottomRight);
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
