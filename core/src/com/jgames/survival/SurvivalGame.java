package com.jgames.survival;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jgames.survival.control.clickhandlers.CommandButtonClickHandler;
import com.jgames.survival.control.clickhandlers.MapCellClickHandler;
import com.jgames.survival.control.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.control.uiscripts.scriptmachines.MultipleActiveScriptMachine;
import com.jgames.survival.ui.JavaClassUIComponentRegistrar;
import com.jgames.survival.ui.UIComponentRegistrar;
import com.jgames.survival.ui.UIElements;
import com.jgames.survival.ui.uicomponents.CommandPanelComponent;
import com.jgames.survival.ui.uicomponents.LeftTopInformationComponent;
import com.jgames.survival.ui.uicomponents.MapTableComponent;
import com.jgames.survival.utils.GameProperties;

public class SurvivalGame extends ApplicationAdapter { //TODO переделать на скрины
	private boolean isDebugMode;

	private Stage stage;

	private Texture boardingTexture;

	@Override
	public void create() {
		initGlobalParameters();

		boardingTexture = new Texture("cell.png"); //TODO добавить инициализацию графики

		DispatcherUIScriptMachine scriptMachine = new MultipleActiveScriptMachine();
		stage = new Stage(new ScreenViewport());

		UIComponentRegistrar componentRegistrar = new JavaClassUIComponentRegistrar(scriptMachine, stage);

		componentRegistrar.registerComponent(new MapTableComponent(20, 20, new MapCellClickHandler(scriptMachine)));
		componentRegistrar.registerComponent(new LeftTopInformationComponent(boardingTexture, 300, 300));
		componentRegistrar.registerComponent(new CommandPanelComponent(new CommandButtonClickHandler(scriptMachine)));

		UIElements uiElements = componentRegistrar.createInterface();

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
		stage.dispose();
		boardingTexture.dispose();
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
		}
		catch (IOException e) {
			Gdx.app.error(SurvivalGame.class.getSimpleName(), "Error with global settings", e);
			GameProperties defaultProperties = new GameProperties();
			defaultProperties.setProperty("isDebugEnable", false);
			return defaultProperties;
		}
	}
}
