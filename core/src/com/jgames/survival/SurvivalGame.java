package com.jgames.survival;

import static ru.jengine.utils.CollectionUtils.toList;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jgames.survival.model.AbstractGameHandler;
import com.jgames.survival.model.GameConfiguration;
import com.jgames.survival.model.MainGameHandler;
import com.jgames.survival.utils.CriticalException;
import com.jgames.survival.utils.GameProperties;
import com.jgames.survival.utils.GdxLogger;
import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.Constraint;
import com.jgames.survival.view.core.factories.JavaUIFactoringConfiguration;
import com.jgames.survival.view.core.factories.impl.ButtonGenerator;
import com.jgames.survival.view.core.factories.impl.ButtonsPanelDisplayFactory;
import com.jgames.survival.view.core.factories.impl.ComplexConstraintDisplayFactory;
import com.jgames.survival.viewmodel.core.UpdatingSystemsManager;
import com.jgames.survival.viewmodel.jenginemodules.ViewModelBaseModule;

public class SurvivalGame extends ApplicationAdapter {
    private boolean isDebugMode;

    private AbstractGameHandler gameHandler;
    private JEngineContainer container;
    private UIManagementSystem uiManagementSystem;
    private UpdatingSystemsManager updatingSystemsManager;

    @Override
    public void create() {
        try {
            initGlobalParameters();
        }
        catch (IOException e) {
            throw new CriticalException("Global parameters can not be set", e);
        }

        Stage stage = new Stage(new ScreenViewport());

        Logger logger = new GdxLogger();
        gameHandler = new MainGameHandler(new GameConfiguration(logger)
                .setDebug(isDebugMode)
        );
        gameHandler.start();

        container = new JEngineContainer();
        container.initializeCommonContexts(ContainerConfiguration
                .builder(ViewModelBaseModule.class)
                .addAdditionalBean(stage)
                .addAdditionalBean(gameHandler)
                .addAdditionalBean(logger)
                .build()
        );

        gameHandler.onStart();

        List<Constraint> constraints = toList(container.getBean(Constraint.class));
        List<ButtonGenerator> buttonGenerators = toList(container.getBean(ButtonGenerator.class));

        updatingSystemsManager = container.getBean(UpdatingSystemsManager.class);

        uiManagementSystem = container.getBean(UIManagementSystem.class);
        uiManagementSystem.setDebug(isDebugMode);

        uiManagementSystem.configure(JavaUIFactoringConfiguration.create()
                .addDisplayFactory("complexConstraintFactory", new ComplexConstraintDisplayFactory(constraints, logger))
                .addDisplayFactory("buttonsPanelDisplay", new ButtonsPanelDisplayFactory(buttonGenerators, logger))
                .build());
        uiManagementSystem.buildInterface();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        uiManagementSystem.resize(width, height);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        updatingSystemsManager.updateElements();
        uiManagementSystem.draw();
    }

    @Override
    public void dispose() {
        gameHandler.stopGame();
        try {
            gameHandler.join();
        } catch (InterruptedException e) {
            Gdx.app.error("INTERRUPTED", "Game was interrupted and not joining", e);
        }

        List<Disposable> disposables = container.getBean(Disposable.class);
        disposables.forEach(Disposable::dispose);
    }

    private void initGlobalParameters() throws IOException {
        GameProperties globalSettings = getGlobalSettings();
        isDebugMode = globalSettings.getProperty("isDebugEnable");
    }

    private static GameProperties getGlobalSettings() throws IOException {
        FileHandle globalSettingsUrl = Gdx.files.internal("gameconfig.properties");
        Properties globalSettings = new Properties();
        globalSettings.load(globalSettingsUrl.read());
        return new GameProperties(globalSettings, true);
    }
}
