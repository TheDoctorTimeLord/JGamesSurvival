package com.jgames.survival.ui.uifactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;
import com.jgames.survival.ui.assets.SimpleTextureStorage.Constants;
import com.jgames.survival.ui.widgets.TextListWidget;

public class LeftTopInformationFactory implements UIFactory {
    private static final String SHOW_TEXT = "Show information";
    private static final String HIDE_TEXT = "Hide information";
    private static final float PAD_BETWEEN_BUTTON_AND_INFO_PANEL = 2;

    private final int informationPanelWidth;
    private final int informationPanelHeight;

    private TextListWidget listWidget;
    private Table alignTable;

    public LeftTopInformationFactory(int informationPanelWidth, int informationPanelHeight) {
        this.informationPanelWidth = informationPanelWidth;
        this.informationPanelHeight = informationPanelHeight;
    }

    @Override
    public void prepareComponents(UIElements uiElements) {
        NinePatch backgroundNinePatch = uiElements.getTextureStorage().createNinePatch(Constants.BUTTON_BACKGROUND);

        NinePatch buttonBackground = new NinePatch(backgroundNinePatch);
        buttonBackground.setMiddleHeight(5);
        buttonBackground.setMiddleWidth(informationPanelWidth);

        createListWidget(backgroundNinePatch);
        Container<?> listWidgetWrapper = configureInformationPanelWrapper(informationPanelWidth, informationPanelHeight);
        TextButton hideShowButton = createHideShowButton(listWidgetWrapper, buttonBackground);
        createFrontWidget(listWidgetWrapper, hideShowButton);
    }

    private void createListWidget(NinePatch backgroundNinePatch) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = new Color(0f, 0f, 0f, 1);

        listWidget = new TextListWidget(backgroundNinePatch, "infoPanel");
        listWidget.setLabelStyle(labelStyle);
    }

    private Container<?> configureInformationPanelWrapper(int informationPanelWidth, int informationPanelHeight) {
        Container<?> listWidgetWrapper = listWidget.getFrontWrapper();
        listWidgetWrapper.width(informationPanelWidth);
        listWidgetWrapper.height(informationPanelHeight);
        listWidgetWrapper.setVisible(false);
        return listWidgetWrapper;
    }

    private static TextButton createHideShowButton(Container<?> listWidgetWrapper, NinePatch buttonBackground) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = new Color(0, 0, 0, 1);
        textButtonStyle.up = new NinePatchDrawable(buttonBackground);
        textButtonStyle.down = new NinePatchDrawable(buttonBackground).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
        TextButton hideShowButton = new TextButton(SHOW_TEXT, textButtonStyle);
        hideShowButton.addListener(new HideShowButtonClickListener(listWidgetWrapper, hideShowButton));
        return hideShowButton;
    }

    private void createFrontWidget(Container<?> listWidgetWrapper, TextButton hideShowButton) {
        alignTable = new Table();
        alignTable.align(Align.topLeft);
        alignTable.setFillParent(true);

        alignTable.add(hideShowButton).padBottom(PAD_BETWEEN_BUTTON_AND_INFO_PANEL).row();
        alignTable.add(listWidgetWrapper);
    }

    @Override
    public Actor getFrontWidget() {
        return alignTable;
    }

    @Override
    public Actor getActionableComponent() {
        return listWidget;
    }

    private static class HideShowButtonClickListener extends ClickListener {
        private final Container<?> listWidgetWrapper;
        private final TextButton hideShowButton;

        public HideShowButtonClickListener(Container<?> listWidgetWrapper, TextButton hideShowButton) {
            this.listWidgetWrapper = listWidgetWrapper;
            this.hideShowButton = hideShowButton;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            boolean isVisible = listWidgetWrapper.isVisible();
            listWidgetWrapper.addAction(Actions.visible(!isVisible));
            hideShowButton.setText(isVisible ? SHOW_TEXT : HIDE_TEXT);
        }
    }
}
