package com.jgames.survival.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.control.uiscripts.UIScriptMachine;
import com.jgames.survival.model.GameActionSender;

public class JavaClassUIComponentRegistrar implements UIComponentRegistrar {
    private final UIElements uiElements;
    private final List<UIComponent> registeredComponents = new ArrayList<>();

    public JavaClassUIComponentRegistrar(UIScriptMachine scriptMachine, Stage stage, GameActionSender actionSender) {
        this.uiElements = new UIElements(scriptMachine, stage, actionSender);
    }

    @Override
    public void registerComponent(UIComponent component) {
        registeredComponents.add(component);
    }

    @Override
    public UIElements createInterface() {
        registeredComponents.forEach(this::initializeComponent);
        return uiElements;
    }

    private void initializeComponent(UIComponent component) {
        component.prepareComponent(uiElements);

        uiElements.getGameStage().addActor(component.getFrontWidget());

        Actor actionableComponent = component.getActionableComponent();
        if (actionableComponent != null) {
            uiElements.addWidgets(actionableComponent);
        }
    }
}
