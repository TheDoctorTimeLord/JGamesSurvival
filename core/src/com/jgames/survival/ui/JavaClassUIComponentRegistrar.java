package com.jgames.survival.ui;

import java.util.ArrayList;
import java.util.List;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.presenter.core.gamestate.PresentingGameState;
import com.jgames.survival.presenter.core.uiscripts.UIScriptMachine;
import com.jgames.survival.ui.assets.TextureStorage;

@Bean
public class JavaClassUIComponentRegistrar implements UIComponentRegistrar {
    private final UIElements uiElements;
    private final List<UIFactory> registeredComponents = new ArrayList<>();

    public JavaClassUIComponentRegistrar(UIScriptMachine scriptMachine, Stage stage, GameActionSender actionSender,
            PresentingGameState presentingGameState, TextureStorage textureStorage) {
        this.uiElements = new UIElements(scriptMachine, stage, actionSender, presentingGameState, textureStorage);
    }

    @Override
    public UIComponentRegistrar registerComponent(UIFactory component) {
        registeredComponents.add(component);
        return this;
    }

    @Override
    public UIElements createInterface() {
        registeredComponents.forEach(this::initializeComponent);
        return uiElements;
    }

    private void initializeComponent(UIFactory component) {
        component.prepareComponents(uiElements);

        uiElements.getGameStage().addActor(component.getFrontWidget());

        Actor actionableComponent = component.getActionableComponent();
        if (actionableComponent != null) {
            uiElements.addWidgets(actionableComponent);
        }
    }
}
