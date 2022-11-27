package com.jgames.survival.view.core;

import static com.jgames.survival.view.core.Constants.DISPLAY_DEFAULT_BACKGROUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.view.core.assets.TextureStorage;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.factories.UIFactoringConfiguration;
import com.jgames.survival.view.core.factories.UIFactoringManager;
import com.jgames.survival.view.core.uielements.UIElementManager;
import com.jgames.survival.viewmodel.core.UpdatableOnGameTick;

@Bean
public class UIManagementSystem implements UpdatableOnGameTick {
    private final DefaultDisplay defaultDisplay;
    private final Logger logger;

    private final List<Display> boundedDisplays = new ArrayList<>();
    private final Stage stage;
    private final UIElementManager uiElementManager;
    private final UIFactoringManager uiFactoringManager;
    private final InterfaceCreator interfaceCreator;

    public UIManagementSystem(Logger logger, Stage stage, UIElementManager uiElementManager,
            UIFactoringManager uiFactoringManager, TextureStorage textureStorage, InterfaceCreator interfaceCreator) {
        this.stage = stage;
        this.uiElementManager = uiElementManager;
        this.uiFactoringManager = uiFactoringManager;
        this.defaultDisplay = new DefaultDisplay(textureStorage.createSprite(DISPLAY_DEFAULT_BACKGROUND));
        this.logger = logger;
        this.interfaceCreator = interfaceCreator;
    }

    public void configure(UIFactoringConfiguration factoringConfiguration) {
        uiFactoringManager.configure(factoringConfiguration);
    }

    public void buildInterface() {
        interfaceCreator.create(this);
    }

    @Override
    public void update() {
        stage.act();
    }

    public void draw() {
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        for (Display display : boundedDisplays) {
            display.resize(width, height);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Display buildDisplay(String displayFactoringCode, Map<String, Object> properties) {
        try {
            return uiFactoringManager.getDisplayFactory(displayFactoringCode).buildDisplay(properties);
        }
        catch (UIException e) {
            logger.error("UISystem", e.getMessage(), e);
            return null;
        }
    }

    public Display bindDisplay(String displayFactoringCode, Map<String, Object> properties) {
        Display display = buildDisplay(displayFactoringCode, properties);
        if (display == null) {
            display = defaultDisplay.clone();
        }

        bindDisplay(display);

        return display;
    }

    public void bindDisplay(Display display) {
        stage.addActor(display.asActor());
        boundedDisplays.add(display);
        display.onBind();
        display.show();
    }

    public void unbindDisplay(Display display) {
        display.hide();
        display.onUnbind();
        stage.getRoot().removeActor(display.asActor());
        boundedDisplays.remove(display);
    }

    public UIElementManager getUiElementManager() {
        return uiElementManager;
    }

    public void setDebug(boolean isDebug) {
        stage.setDebugAll(isDebug);
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
        public DefaultDisplay clone() {
            return new DefaultDisplay(defaultBackground.getRegion());
        }
    }
}
