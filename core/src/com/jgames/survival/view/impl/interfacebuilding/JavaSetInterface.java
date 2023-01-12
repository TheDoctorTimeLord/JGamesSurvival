package com.jgames.survival.view.impl.interfacebuilding;

import java.util.Arrays;

import ru.jengine.beancontainer.annotations.Bean;

import com.google.common.collect.ImmutableMap;
import com.jgames.survival.model.GameActionSender;
import com.jgames.survival.view.core.InterfaceCreator;
import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.ComplexConstraintDisplay;
import com.jgames.survival.view.core.displays.impl.ButtonsPanelDisplay;
import com.jgames.survival.view.core.displays.impl.ComplexConstraintDisplayImpl;
import com.jgames.survival.view.core.displays.impl.aligments.AlignBottomRight;
import com.jgames.survival.view.core.factories.impl.ButtonsPanelDisplayFactory;
import com.jgames.survival.view.core.factories.impl.ComplexConstraintDisplayFactory;
import com.jgames.survival.view.impl.factories.buttons.NextPhaseButtonGenerator;
import com.jgames.survival.view.impl.factories.buttons.NextTurnButtonGenerator;

@Bean
public class JavaSetInterface implements InterfaceCreator {
    private final GameActionSender actionSender;

    public JavaSetInterface(GameActionSender actionSender) {
        this.actionSender = actionSender;
    }

    @Override
    public void create(UIManagementSystem uiSystem) {
        ComplexConstraintDisplay rootDisplay = createRootDisplay(uiSystem);
        createPhaseTurnDisplay(uiSystem, rootDisplay);
    }

    private ComplexConstraintDisplay createRootDisplay(UIManagementSystem uiSystem) {
        ComplexConstraintDisplayImpl rootDisplay = (ComplexConstraintDisplayImpl)uiSystem.bindDisplay(
                        "complexConstraintFactory", "rootDisplay",
                        ImmutableMap.of(ComplexConstraintDisplayFactory.IS_FILL_SCREEN, true)
                );
        rootDisplay.asActor().validate();

        return rootDisplay;
    }

    private void createPhaseTurnDisplay(UIManagementSystem uiSystem, ComplexConstraintDisplay rootDisplay) {
        ButtonsPanelDisplay phaseTurnDisplay = createDisplayButtons(uiSystem, "phaseAndTurn", true,
                NextPhaseButtonGenerator.BUTTON_NAME,
                NextTurnButtonGenerator.BUTTON_NAME);
        rootDisplay.bindDisplay(phaseTurnDisplay, AlignBottomRight.NAME);
    }

    private ButtonsPanelDisplay createDisplayButtons(UIManagementSystem uiManagementSystem,
            String displayName, boolean vertical, String... buttonNames)
    {
        return (ButtonsPanelDisplay)uiManagementSystem.buildDisplay("buttonsPanelDisplay", displayName, ImmutableMap.of(
                ButtonsPanelDisplayFactory.AVAILABLE_BUTTONS, Arrays.asList(buttonNames),
                ButtonsPanelDisplayFactory.VERTICAL, vertical,
                ButtonsPanelDisplayFactory.BUTTON_PADDING, 10f
        ));
    }
}
