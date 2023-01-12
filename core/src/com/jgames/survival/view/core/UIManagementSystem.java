package com.jgames.survival.view.core;

import static com.jgames.survival.view.core.Constants.DISPLAY_DEFAULT_BACKGROUND;

import java.util.Map;

import javax.annotation.Nullable;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jgames.survival.view.core.assets.TextureStorage;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.view.core.displays.impl.RootDisplay;
import com.jgames.survival.view.core.factories.UIFactoringConfiguration;
import com.jgames.survival.view.core.factories.UIFactoringManager;
import com.jgames.survival.viewmodel.core.UpdatableOnGameTick;

@Bean
public class UIManagementSystem implements UpdatableOnGameTick {
    private final DefaultDisplay defaultDisplay;
    private final Logger logger;

    private final RootDisplay rootDisplay;
    private final Stage stage;
    private final UIFactoringManager uiFactoringManager;
    private final InterfaceCreator interfaceCreator;
    private final UIEventBus eventBus;

    public UIManagementSystem(Logger logger, Stage stage, UIFactoringManager uiFactoringManager,
            TextureStorage textureStorage, InterfaceCreator interfaceCreator, UIEventBus eventBus) {
        this.stage = stage;
        this.uiFactoringManager = uiFactoringManager;
        this.defaultDisplay = new DefaultDisplay(textureStorage.createSprite(DISPLAY_DEFAULT_BACKGROUND));
        this.logger = logger;
        this.interfaceCreator = interfaceCreator;
        this.eventBus = eventBus;

        this.rootDisplay = new RootDisplay(stage, eventBus);
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
        rootDisplay.resize(width, height);
    }

    public Stage getStage() {
        return stage;
    }

    public RootDisplay getRootDisplay() {
        return rootDisplay;
    }

    public Display buildDisplay(String displayFactoringCode, String displayName, Map<String, Object> properties) {
        try {
            return uiFactoringManager.getDisplayFactory(displayFactoringCode).buildDisplay(displayName, properties);
        }
        catch (UIException e) {
            logger.error("UISystem", e.getMessage(), e);
            return null;
        }
    }

    public Display bindDisplay(String displayFactoringCode, String displayName, Map<String, Object> properties) {
        Display display = buildDisplay(displayFactoringCode, displayName, properties);
        if (display == null) {
            display = defaultDisplay.clone();
        }

        bindDisplay(display);

        return display;
    }

    public void bindDisplay(Display display) {
        rootDisplay.bindDisplay(display);
    }

    public void unbindDisplay(Display display) {
        rootDisplay.unbindDisplay(display);
    }

    @Nullable
    public CanBeActor findByPath(String... path) {
        if (path.length == 0) {
            return null;
        }

        Display currentDisplay = rootDisplay;
        for (int i = 0; i < path.length - 1; i++) {
            String pathPart = path[i];
            CanBeActor canBeActor = currentDisplay.findNamedElement(pathPart);
            if (!(canBeActor instanceof Display display)) {
                return null;
            }

            currentDisplay = display;
        }

        return currentDisplay.findNamedElement(path[path.length - 1]);
    }

    public void setDebug(boolean isDebug) {
        stage.setDebugAll(isDebug);
        eventBus.setEnableDebugEventInfo(isDebug);
    }

    public static class DefaultDisplay implements Display, Cloneable {
        private final TextureRegionDrawable defaultBackground;
        private final Image defaultBackgroundActor;

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

        @Override
        public String getName() {
            return "defaultDisplay";
        }

        @Override
        public void onBind(UIEventBus eventBus, @Nullable Display parent) { }

        @Override
        public void onUnbind(UIEventBus eventBus) { }

        @Nullable
        @Override
        public Display getParent() {
            return null;
        }

        @Override
        public CanBeActor findNamedElement(String elementName) {
            return null;
        }
    }
}
