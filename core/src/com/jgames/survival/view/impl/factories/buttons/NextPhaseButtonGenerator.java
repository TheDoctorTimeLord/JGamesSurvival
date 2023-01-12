package com.jgames.survival.view.impl.factories.buttons;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.jgames.survival.view.core.assets.TextureStorage;

@Bean
public class NextPhaseButtonGenerator extends BaseButtonGenerator {
    public static final String BUTTON_NAME = "nextPhaseButton";

    public NextPhaseButtonGenerator(TextureStorage textureStorage) {
        super(textureStorage, BUTTON_NAME, "Next phase");
    }

    @Override
    protected void configureButton(ImageTextButton button) {
        button.setWidth(80);
        button.setHeight(60);
        button.setDisabled(true);
    }
}
