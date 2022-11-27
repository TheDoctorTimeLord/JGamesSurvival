package com.jgames.survival.view.impl.factories.buttons;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.jgames.survival.view.core.assets.TextureStorage;

@Bean
public class TestButtonGenerator extends BaseButtonGenerator {
    public TestButtonGenerator(TextureStorage textureStorage) {
        super(textureStorage, "testButton", "Test");
    }

    @Override
    protected void configureButton(ImageTextButton button) { }
}
