package com.jgames.survival.ui.uicomponents;

import java.util.Arrays;
import java.util.stream.Stream;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import com.jgames.survival.control.clickhandlers.PhaseOrTurnClickedHandler;
import com.jgames.survival.control.uiscripts.EmptyScriptState;
import com.jgames.survival.control.uiscripts.sctipts.CyclicUIScript;
import com.jgames.survival.ui.UIComponent;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.ApplyPhaseChanges;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.CallUpdateGame;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandlePhaseButton;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.EndHandleTurnButton;
import com.jgames.survival.ui.uiscriptelements.phaseturnpanel.WaitPhaseOrTurnClicked;
import com.jgames.survival.ui.widgets.GlobalMapWrapper;
import com.jgames.survival.ui.widgets.MapCell;

public class PhaseAndTurnPanelComponent implements UIComponent {
    private static final int BOARD_SIZE = 4;
    private static final int BUTTON_MIDDLE_HEIGHT = 50;
    private static final int BUTTON_MIDDLE_WIDTH = 120;

    private final NinePatch buttonsBackground;
    private final PhaseOrTurnClickedHandler buttonCallback;
    private final TextureRegion[] directedPersonTextures;

    private Table panel;

    public PhaseAndTurnPanelComponent(Texture buttonsBackground, PhaseOrTurnClickedHandler callback, Texture... directedPerson) {
        this.directedPersonTextures = Stream.of(directedPerson)
                .map(TextureRegion::new)
                .toArray(TextureRegion[]::new);
        this.buttonCallback = callback;
        this.buttonsBackground = new NinePatch(buttonsBackground, BOARD_SIZE, BOARD_SIZE, BOARD_SIZE, BOARD_SIZE);
        this.buttonsBackground.setMiddleHeight(BUTTON_MIDDLE_HEIGHT);
        this.buttonsBackground.setMiddleWidth(BUTTON_MIDDLE_WIDTH);
    }

    @Override
    public void prepareComponent(UIElements uiElements) {
        TextButton nextPhaseButton = createButton("Next Phase");
        TextButton nextTurnButton = createButton("Next Turn");

        nextPhaseButton.setDisabled(true);

        WaitPhaseOrTurnClicked waitTurnClicked = new WaitPhaseOrTurnClicked(nextTurnButton);
        CallUpdateGame updateGame = new CallUpdateGame(uiElements.getActionSender());
        EndHandleTurnButton endHandleTurn = new EndHandleTurnButton(nextTurnButton, nextPhaseButton);
        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>(
                Arrays.asList(waitTurnClicked, updateGame, endHandleTurn),
                new EmptyScriptState(),
                "handleTurnButton"
        ));

        GlobalMapWrapper<MapCell> globalMap = uiElements.getWidget(GlobalMapWrapper.GLOBAL_MAP_NAME, GlobalMapWrapper.class);

        WaitPhaseOrTurnClicked waitPhaseClicked = new WaitPhaseOrTurnClicked(nextPhaseButton);
        ApplyPhaseChanges applyPhaseChanges = new ApplyPhaseChanges(uiElements.getPresentingGameState(), globalMap,
                directedPersonTextures);
        EndHandlePhaseButton endHandlePhase = new EndHandlePhaseButton(uiElements.getPresentingGameState(), nextTurnButton, nextPhaseButton);
        uiElements.getScriptMachine().registerScript(new CyclicUIScript<>(
                Arrays.asList(waitPhaseClicked, applyPhaseChanges, endHandlePhase),
                new EmptyScriptState(),
                "handlePhaseButton"
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
