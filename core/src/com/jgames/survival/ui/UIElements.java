package com.jgames.survival.ui;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jgames.survival.control.uiscripts.UIScriptMachine;
import com.jgames.survival.model.GameActionSender;

public class UIElements {
    private final UIScriptMachine scriptMachine;
    private final Stage gameStage;
    private final GameActionSender actionSender;
    private final Map<String, Actor> widgets = new ConcurrentHashMap<>();

    public UIElements(UIScriptMachine scriptMachine, Stage gameStage,
            GameActionSender actionSender, Actor... widgets) {
        this.scriptMachine = scriptMachine;
        this.gameStage = gameStage;
        this.actionSender = actionSender;

        addWidgets(widgets);
    }

    public UIScriptMachine getScriptMachine() {
        return scriptMachine;
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public GameActionSender getActionSender() {
        return actionSender;
    }

    public void addWidgets(Actor... widgets) {
        for (Actor widget : widgets) {
            String widgetName = widget.getName();
            if (widgetName == null) {
                throw new IllegalArgumentException("Widget [" + widget + "] doesn't have name");
            }
            this.widgets.put(widgetName, widget);
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
