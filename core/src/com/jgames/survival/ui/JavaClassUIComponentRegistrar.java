package com.jgames.survival.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.control.uiscripts.UIScriptMachine;

public class JavaClassUIComponentRegistrar implements UIComponentRegistrar {
    private final UIElements uiElements;
    private final List<UIComponent> registeredComponents = new ArrayList<>();

    public JavaClassUIComponentRegistrar(UIScriptMachine scriptMachine, Stage stage) {
        this.uiElements = new UIElements(scriptMachine, stage);
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
