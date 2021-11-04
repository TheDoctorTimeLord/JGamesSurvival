package com.jgames.survival;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jgames.survival.control.clickhandlers.CommandButtonClickHandler;
import com.jgames.survival.control.clickhandlers.MapCellClickHandler;
import com.jgames.survival.control.uiscripts.DispatcherUIScriptMachine;
import com.jgames.survival.control.uiscripts.scriptmachines.MultipleActiveScriptMachine;
import com.jgames.survival.ui.CommandPanel;
import com.jgames.survival.ui.MapTable;
import com.jgames.survival.ui.TextInformation;
import com.jgames.survival.ui.UIElements;

public class SurvivalGame extends ApplicationAdapter { //TODO переделать на скрины
	private static final boolean DEBUG_MODE = false;

	private Stage stage;
	private ScrollPane textInformationScrollPane;

	@Override
	public void create () {
		DispatcherUIScriptMachine scriptMachine = new MultipleActiveScriptMachine();

		stage = new Stage(new ScreenViewport());

		MapTable globalMapTable = new MapTable(20, 20, new MapCellClickHandler(scriptMachine));
		ScrollPane globalMapTableScrollPane = globalMapTable.getScrollPaneWrapper();
		globalMapTableScrollPane.setFillParent(true);
		stage.addActor(globalMapTableScrollPane);

		TextInformation textInformation = new TextInformation();
		textInformationScrollPane = textInformation.getScrollPaneWrapper(300, 600);
		textInformationScrollPane.setPosition(0, stage.getHeight() - 600);
		stage.addActor(textInformationScrollPane);

		UIElements uiElements = new UIElements(scriptMachine, textInformation);

		CommandPanel commandPanel = new CommandPanel(uiElements, new CommandButtonClickHandler(scriptMachine));
		commandPanel.align(Align.center | Align.bottom);
		commandPanel.setFillParent(true);
		stage.addActor(commandPanel);

		if (DEBUG_MODE) {
			globalMapTable.setDebug(true);
			globalMapTableScrollPane.setDebug(true);
			commandPanel.setDebug(true);
			textInformation.setDebug(true);
		}

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		textInformationScrollPane.setPosition(0, stage.getHeight() - 600);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
