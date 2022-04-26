package com.jgames.survival;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jgames.survival.model.AbstractGameHandler;
import com.jgames.survival.model.GameConfiguration;
import com.jgames.survival.model.MainGameHandler;
import com.jgames.survival.presenter.core.changeshangling.GameChangeHandlersRegistrar;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.presenter.core.uiscripts.scriptmachines.MultipleActiveScriptMachine;
import com.jgames.survival.presenter.filling.changeshandling.AvailableObjectTypeNameHandler;
import com.jgames.survival.presenter.filling.changeshandling.BattleActionWrapperHandler;
import com.jgames.survival.presenter.filling.changeshandling.StartPhaseChangesHandler;
import com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers.DealingDamageNotificationHandler;
import com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers.ModelHpActionHandler;
import com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers.ObjectTypeActionHandler;
import com.jgames.survival.presenter.filling.changeshandling.battleactionhandlers.StartPositionActionHandler;
import com.jgames.survival.presenter.filling.clickactionhandlers.CommandButtonClickHandler;
import com.jgames.survival.presenter.filling.clickactionhandlers.MapCellClickHandler;
import com.jgames.survival.presenter.filling.clickactionhandlers.PhaseOrTurnClickedHandler;
import com.jgames.survival.presenter.filling.gamestate.modules.DrawingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.MapFillingModule;
import com.jgames.survival.presenter.filling.gamestate.modules.ModelDataModule;
import com.jgames.survival.presenter.filling.gamestate.modules.NameObjectResolvingModule;
import com.jgames.survival.presenter.filling.gamestate.mutators.DrawingRegistrar;
import com.jgames.survival.presenter.filling.gamestate.mutators.FakeNameObjectResolvingModuleMutator;
import com.jgames.survival.presenter.filling.gamestate.mutators.ModelDataMutator;
import com.jgames.survival.ui.JavaClassUIComponentRegistrar;
import com.jgames.survival.ui.UIComponentRegistrar;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.uifactories.CommandPanelFactory;
import com.jgames.survival.ui.uifactories.LeftTopInformationFactory;
import com.jgames.survival.ui.uifactories.MapTableFactory;
import com.jgames.survival.ui.uifactories.PhaseAndTurnPanelFactory;
import com.jgames.survival.ui.uiscriptelements.mappanel.UpdateMapAction;
import com.jgames.survival.utils.GameProperties;
import com.jgames.survival.utils.assets.SimpleTextureStorage;
import com.jgames.survival.utils.assets.TextureStorage;
import com.jgames.survival.utils.assets.TextureStorageConfiguration;

public class SurvivalGame extends ApplicationAdapter { //TODO переделать на скрины
    private boolean isDebugMode;

    private AbstractGameHandler gameHandler;
    private Stage stage;
    private TextureStorage textureStorage;

    @Override
    public void create() {
        initGlobalParameters();

        textureStorage = new SimpleTextureStorage().load(new TextureStorageConfiguration());

        gameHandler = new MainGameHandler(new GameConfiguration());
        gameHandler.start();

        PresentingGameState presentingGameState = new PresentingGameState()
                .addStateModule(new ModelDataModule())
                .addStateModule(new MapFillingModule())
                .addStateModule(new DrawingModule(textureStorage))
                .addStateModule(new NameObjectResolvingModule())
                .addModuleMutator(new ModelDataMutator())
                .addModuleMutator(new DrawingRegistrar())
                .addModuleMutator(new FakeNameObjectResolvingModuleMutator())
                .connectMutatorsWithModules();

        GameChangeHandlersRegistrar gameChangeHandlersRegistrar = new GameChangeHandlersRegistrar(gameHandler, presentingGameState)
                .registerGameChangeHandler(new StartPhaseChangesHandler())
                .registerGameChangeHandler(new AvailableObjectTypeNameHandler(textureStorage))
                .registerGameChangeHandler(new BattleActionWrapperHandler(
                        new StartPositionActionHandler(),
                        new ObjectTypeActionHandler(),
                        new ModelHpActionHandler(),
                        new DealingDamageNotificationHandler()
                ));

        gameHandler.onStart();

        DispatcherUIScriptMachine scriptMachine = new MultipleActiveScriptMachine();
        stage = new Stage(new ScreenViewport());

        UIComponentRegistrar componentRegistrar = new JavaClassUIComponentRegistrar(scriptMachine, stage, gameHandler, presentingGameState, textureStorage)
                .registerComponent(new MapTableFactory(5, 5, new MapCellClickHandler(scriptMachine)))
                .registerComponent(new LeftTopInformationFactory(300, 300))
                .registerComponent(new CommandPanelFactory(new CommandButtonClickHandler(scriptMachine)))
                .registerComponent(new PhaseAndTurnPanelFactory(new PhaseOrTurnClickedHandler(scriptMachine)));

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

        stage.dispose();
        textureStorage.dispose();
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
