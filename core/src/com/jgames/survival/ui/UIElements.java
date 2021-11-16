package com.jgames.survival.ui;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.control.uiscripts.UIScriptMachine;

public class UIElements {
    private final UIScriptMachine scriptMachine;
    private final Stage gameStage;
    private final Map<String, Actor> widgets = new ConcurrentHashMap<>();

    public UIElements(UIScriptMachine scriptMachine, Stage gameStage, Actor... widgets) {
        this.scriptMachine = scriptMachine;
        this.gameStage = gameStage;

        addWidgets(widgets);
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public UIScriptMachine getScriptMachine() {
        return scriptMachine;
    }

    public void addWidgets(Actor... widgets) {
        for (Actor widget : widgets) {
            this.widgets.put(widget.getName(), widget);
        }
    }

    @SuppressWarnings("unchecked")
    public <W extends Actor> W getWidget(String widgetName, Class<W> widgetClass) {
        Actor requestedWidget = widgets.get(widgetName);
        return widgetClass.isInstance(requestedWidget) ? (W)requestedWidget : null;
    }

    public void setDebug(boolean isDebug) {
        gameStage.setDebugAll(isDebug);
    }
}
