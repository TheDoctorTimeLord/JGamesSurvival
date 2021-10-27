package com.jgames.survival.ui.clickable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CommandButton extends TextButton {
    public CommandButton(String text, TextureRegion region, ClickOnCommandButton callback) {
        super(text, createImageButtonStyle(region));
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.clicked(event, CommandButton.this);
            }
        });
    }

    private static TextButtonStyle createImageButtonStyle(TextureRegion region) {
        TextButtonStyle style = new TextButtonStyle(); //TODO придумать как переработать создание команды
        style.up = new TextureRegionDrawable(region);
        style.down = new TextureRegionDrawable(region).tint(new Color(0.9f, 0.9f, 0.9f, 1));
        style.font = new BitmapFont();
        style.fontColor = new Color(0, 0, 0, 1);
        return style;
    }

    @FunctionalInterface
    public interface ClickOnCommandButton {
        void clicked(InputEvent event, CommandButton commandButton);
    }
}
