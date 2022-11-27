package com.jgames.survival.view.impl.factories.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.view.core.assets.TextureStorage;
import com.jgames.survival.view.core.factories.impl.ButtonGenerator;

public abstract class BaseButtonGenerator implements ButtonGenerator {
    private final TextureStorage textureStorage;
    private final String buttonName;
    private final String buttonTitle;

    public BaseButtonGenerator(TextureStorage textureStorage, String buttonName, String buttonTitle) {
        this.textureStorage = textureStorage;
        this.buttonName = buttonName;
        this.buttonTitle = buttonTitle;
    }

    @Override
    public ButtonConfiguration generate() {
        Sprite buttonBackGround = textureStorage.createSprite("backgroundButton");

        ImageTextButtonStyle style = new ImageTextButtonStyle();
        style.up = new TextureRegionDrawable(buttonBackGround);
        style.down = new TextureRegionDrawable(buttonBackGround).tint(new Color(0.7f, 0.7f, 0.7f, 0.9f));
        style.disabled = new TextureRegionDrawable(buttonBackGround).tint(new Color(0.6f, 0.6f, 0.6f, 0.9f));
        style.font = new BitmapFont();

        return new ButtonConfiguration(buttonName, () -> {
            ImageTextButton button = new ImageTextButton(buttonTitle, style);
            configureButton(button);
            return button;
        });
    }

    protected abstract void configureButton(ImageTextButton button);
}
