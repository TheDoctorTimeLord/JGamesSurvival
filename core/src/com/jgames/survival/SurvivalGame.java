package com.jgames.survival;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import ru.jengine.beancontainer.dataclasses.ContainerConfiguration;
import ru.jengine.beancontainer.implementation.JEngineContainer;

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
import com.jgames.survival.presenter.core.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.presenter.filling.clickactions.CommandButtonClickHandler;
import com.jgames.survival.presenter.filling.clickactions.MapCellClickHandler;
import com.jgames.survival.presenter.filling.clickactions.ButtonClickedHandler;
import com.jgames.survival.presenter.modules.PresenterAndUIMainModule;
import com.jgames.survival.ui.UIComponentRegistrar;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.uifactories.CommandPanelFactory;
import com.jgames.survival.ui.uifactories.LeftTopInformationFactory;
import com.jgames.survival.ui.uifactories.MapTableFactory;
import com.jgames.survival.ui.uifactories.PhaseAndTurnPanelFactory;
import com.jgames.survival.ui.uifactories.SaveAndLoadPanelFactory;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMapAction;
import com.jgames.survival.utils.GameProperties;

public class SurvivalGame extends ApplicationAdapter { //TODO переделать на скрины
    private boolean isDebugMode;

    private AbstractGameHandler gameHandler;
    private Stage stage;
    private JEngineContainer container;

    @Override
    public void create() {
        initGlobalParameters();

        stage = new Stage(new ScreenViewport());

        gameHandler = new MainGameHandler(new GameConfiguration()
                .setDebug(isDebugMode)
        );
        gameHandler.start();

        container = new JEngineContainer();
        container.initializeCommonContexts(ContainerConfiguration
                .build(PresenterAndUIMainModule.class)
                .addAdditionalBean(stage)
                .addAdditionalBean(gameHandler)
                .addAdditionalBean(gameHandler.getLogger())
        );

        gameHandler.onStart();

        DispatcherUIScriptMachine scriptMachine = container.getBean(DispatcherUIScriptMachine.class);
        ButtonClickedHandler buttonClickedHandler = new ButtonClickedHandler(scriptMachine);

        UIComponentRegistrar componentRegistrar = container.getBean(UIComponentRegistrar.class);
        componentRegistrar
                .registerComponent(new MapTableFactory(5, 5, new MapCellClickHandler(scriptMachine)))
                .registerComponent(new LeftTopInformationFactory(300, 300))
                .registerComponent(new CommandPanelFactory(new CommandButtonClickHandler(scriptMachine)))
                .registerComponent(new PhaseAndTurnPanelFactory(buttonClickedHandler))
                .registerComponent(new SaveAndLoadPanelFactory(buttonClickedHandler));

        UIElements uiElements = componentRegistrar.createInterface();
        scriptMachine.dispatch(new UpdateMapAction(), action -> {});

        Gdx.input.setInputProcessor(stage);

        if (isDebugMode) {
            uiElements.setDebug(true);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.act();
        stage.draw();
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

    private void initGlobalParameters() {
        GameProperties globalSettings = getGlobalSettings();
        isDebugMode = globalSettings.getProperty("isDebugEnable");
    }

    private static GameProperties getGlobalSettings() {
        try {
            FileHandle globalSettingsUrl = Gdx.files.internal("gameconfig.properties");
            Properties globalSettings = new Properties();
            globalSettings.load(globalSettingsUrl.read());
            return new GameProperties(globalSettings, true);
        } catch (IOException e) {
            Gdx.app.error(SurvivalGame.class.getSimpleName(), "Error with global settings", e);
            GameProperties defaultProperties = new GameProperties();
            defaultProperties.setProperty("isDebugEnable", false);
            return defaultProperties;
        }
    }
}
