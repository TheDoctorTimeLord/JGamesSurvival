package com.jgames.survival.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.jgames.survival.presenter.filling.clickactions.ButtonClickedHandler;

public class WidgetUtils {
    public static TextButton createButton(String buttonText, NinePatch buttonBackground,
            ButtonClickedHandler buttonCallback)
    {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = new NinePatchDrawable(buttonBackground);
        textButtonStyle.down = new NinePatchDrawable(buttonBackground).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
        textButtonStyle.disabled = new NinePatchDrawable(buttonBackground).tint(new Color(0.6f, 0.6f, 0.6f, 1f));
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

    public static Table createButtonPanel(int align, TextButton... buttons) {
        Table panel = new Table();

        for (int i = 0; i < buttons.length - 1; i++) {
            panel.add(buttons[i]).pad(5).row();
        }
        panel.add(buttons[buttons.length - 1]).padBottom(5);

        panel.setFillParent(true);
        panel.align(align);

        return panel;
    }
}
