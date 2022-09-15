package com.jgames.survival.view.core;

import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.ui.assets.TextureStorage;
import com.jgames.survival.view.core.factories.UIFactoringManager;
import com.jgames.survival.view.core.uielements.UIElementManager;
import com.jgames.survival.view.core.uielements.displaies.Display;

@Bean
public class UIManagementSystem {
    private static final String DISPLAY_DEFAULT_BACKGROUND = "displayDefaultBackground";

    private final Stage stage;
    private final UIElementManager uiElementManager;
    private final UIFactoringManager uiFactoringManager;
    private final DefaultDisplay defaultDisplay;
    private final Logger logger;

    public UIManagementSystem(Stage stage, UIElementManager uiElementManager, UIFactoringManager uiFactoringManager,
            TextureStorage textureStorage, Logger logger) {
        this.stage = stage;
        this.uiElementManager = uiElementManager;
        this.uiFactoringManager = uiFactoringManager;
        this.defaultDisplay = new DefaultDisplay(textureStorage.createSprite(DISPLAY_DEFAULT_BACKGROUND));
        this.logger = logger;

        //TODO сделать самый верхнеуровневый дисплей
    }

    public void act() {
        stage.act();
    }

    public void drawStage() {
        stage.draw();
    }

    public Display bindDisplay(String displayFactoringCode, Map<String, Object> properties) {
        Display display;
        try {
            display = uiFactoringManager.getDisplayFactory(displayFactoringCode).buildDisplay(properties);
        }
        catch (UIException e) {
            logger.error("UISystem", e.getMessage(), e);
            display = defaultDisplay.clone();
        }

        bindDisplay(display);

        return display;
    }

    public void bindDisplay(Display display) {
        stage.addActor(display.asActor());
    }

    public void unbindDisplay(Display display) {
        stage.getRoot().removeActor(display.asActor());
    }

    public static class DefaultDisplay implements Display, Cloneable {
        private TextureRegionDrawable defaultBackground;
        private Image defaultBackgroundActor;

        public DefaultDisplay(TextureRegion defaultBackground) {
            this.defaultBackground = new TextureRegionDrawable(defaultBackground);
            this.defaultBackgroundActor = new Image(this.defaultBackground);
        }

        @Override
        public Actor asActor() {
            return defaultBackgroundActor;
        }

        @Override
        public void show() {
            defaultBackgroundActor.setVisible(true);
        }

        @Override
        public void hide() {
            defaultBackgroundActor.setVisible(false);
        }

        @Override
        public DefaultDisplay clone() {
            return new DefaultDisplay(defaultBackground.getRegion());
        }
    }
}
