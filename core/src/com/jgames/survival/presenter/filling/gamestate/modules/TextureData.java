package com.jgames.survival.presenter.filling.gamestate.modules;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.UIFactory;

public class TextureData {
    private final String name;
    private final UIElements uiElements;
    private UIFactory textureFactory;
    private TextureRegion textureRegion;

    public TextureData(String name, UIElements uiElements) {
        this.name = name;
        this.uiElements = uiElements;
    }

    public String getName() {
        return name;
    }

    public UIFactory getTextureFactory() {
        return textureFactory;
    }

    public TextureData setTextureFactory(UIFactory textureFactory) {
        this.textureFactory = textureFactory;
        this.textureFactory.prepareComponents(uiElements);
        return this;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public TextureData setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        return this;
    }
}
