package com.jgames.survival.view.impl.interfacebuilding;

import static com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements.BindUIElementScriptElement.BINDING_UI_ELEMENT;

import java.util.Arrays;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.google.common.collect.ImmutableMap;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.model.api.interaction.actions.NewTurnBattleAction;
import com.jgames.survival.view.core.InterfaceCreator;
import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.ComplexConstraintDisplay;
import com.jgames.survival.view.core.displays.impl.ButtonsPanelDisplay;
import com.jgames.survival.view.core.displays.impl.ComplexConstraintDisplayImpl;
import com.jgames.survival.view.core.displays.impl.aligments.AlignBottomRight;
import com.jgames.survival.view.core.displays.impl.aligments.AlignCenter;
import com.jgames.survival.view.core.factories.impl.ButtonsPanelDisplayFactory;
import com.jgames.survival.view.core.factories.impl.ComplexConstraintDisplayFactory;
import com.jgames.survival.view.core.uielements.UIElementWrapper;
import com.jgames.survival.view.impl.displays.BattlefieldDisplay;
import com.jgames.survival.view.impl.factories.BattlefieldDisplayFactory;
import com.jgames.survival.view.impl.factories.buttons.NextPhaseButtonGenerator;
import com.jgames.survival.view.impl.factories.buttons.NextTurnButtonGenerator;
import com.jgames.survival.viewmodel.core.scriptmachine.UIScriptMachine;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.RunnableUISScriptElement;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptBasedElements;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.UIScriptState;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements.BindUIElementScriptElement;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements.TouchedActorElement;

@Bean
public class JavaSetInterface implements InterfaceCreator {
    private final UIScriptMachine scriptMachine;
    private final GameActionSender actionSender;

    public JavaSetInterface(UIScriptMachine scriptMachine, GameActionSender actionSender) {
        this.scriptMachine = scriptMachine;
        this.actionSender = actionSender;
    }

    @Override
    public void create(UIManagementSystem uiSystem) {
        ComplexConstraintDisplay rootDisplay = createRootDisplay(uiSystem);
        createPhaseTurnDisplay(uiSystem, rootDisplay);

        createBattlefieldDisplay(uiSystem, rootDisplay);
    }

    private ComplexConstraintDisplay createRootDisplay(UIManagementSystem uiSystem) {
        ComplexConstraintDisplayImpl rootDisplay = (ComplexConstraintDisplayImpl)uiSystem.bindDisplay(
                        "complexConstraintFactory",
                        ImmutableMap.of(ComplexConstraintDisplayFactory.IS_FILL_SCREEN, true)
                );
        rootDisplay.asActor().validate();

        return rootDisplay;
    }

    private void createPhaseTurnDisplay(UIManagementSystem uiSystem, ComplexConstraintDisplay rootDisplay) {
        ButtonsPanelDisplay phaseTurnDisplay = createDisplayButtons(uiSystem, true,
                NextPhaseButtonGenerator.BUTTON_NAME,
                NextTurnButtonGenerator.BUTTON_NAME);
        rootDisplay.bindDisplay(phaseTurnDisplay, AlignBottomRight.NAME);

        Button nextPhaseButton = (Button)phaseTurnDisplay.findNamedElement(NextPhaseButtonGenerator.BUTTON_NAME);
        Button nextTurnButton = (Button)phaseTurnDisplay.findNamedElement(NextTurnButtonGenerator.BUTTON_NAME);

        scriptMachine.registerScript(new UIScriptBasedElements("nextPhase",
                new TouchedActorElement(nextPhaseButton, UIScriptState.TOUCHED_ACTOR),
                (RunnableUISScriptElement)(context, state) -> {
                    nextPhaseButton.setDisabled(true);
                    nextTurnButton.setDisabled(false);
                }
        ));
        scriptMachine.registerScript(new UIScriptBasedElements("nextTurn",
                new TouchedActorElement(nextTurnButton, UIScriptState.TOUCHED_ACTOR),
                (RunnableUISScriptElement)(context, state) -> {
                    nextPhaseButton.setDisabled(false);
                    nextTurnButton.setDisabled(true);
                },
                (RunnableUISScriptElement)(context, state) -> actionSender.sendGameAction(new NewTurnBattleAction())
        ));
    }

    private ButtonsPanelDisplay createDisplayButtons(UIManagementSystem uiManagementSystem, boolean vertical,
            String... buttonNames)
    {
        return (ButtonsPanelDisplay)uiManagementSystem.buildDisplay("buttonsPanelDisplay", ImmutableMap.of(
                ButtonsPanelDisplayFactory.AVAILABLE_BUTTONS, Arrays.asList(buttonNames),
                ButtonsPanelDisplayFactory.VERTICAL, vertical,
                ButtonsPanelDisplayFactory.BUTTON_PADDING, 10f
        ));
    }

    private void createBattlefieldDisplay(UIManagementSystem uiSystem, ComplexConstraintDisplay rootDisplay) {
        BattlefieldDisplay battlefieldDisplay = (BattlefieldDisplay)uiSystem.buildDisplay("battlefieldDisplay", ImmutableMap.of(
                BattlefieldDisplayFactory.SCALE_POINT_TO_WINDOW, 32f
        ));
        rootDisplay.bindDisplay(battlefieldDisplay, AlignCenter.NAME);

        scriptMachine.registerScript(new UIScriptBasedElements("addBattlefieldComponent",
                new BindUIElementScriptElement("battlefield"),
                (RunnableUISScriptElement)(context, state) ->
                        battlefieldDisplay.addBattlefieldComponent(state.getAs(BINDING_UI_ELEMENT, UIElementWrapper.class))
        ));
    }
}
