package com.jgames.survival.view.impl.interfacebuilding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.impl.ComplexConstraintDisplayImpl;
import com.jgames.survival.view.core.factories.impl.ComplexConstraintDisplayFactory;
import com.jgames.survival.viewmodel.core.scriptmachine.DispatcherUIScriptMachine;

public class RootDisplayBuilder {
    private final UIManagementSystem uiManagementSystem;
    private final DispatcherUIScriptMachine dispatcherUIScriptMachine;

    private List<String> availableConstraintNames = Collections.emptyList();
    private boolean isFillScreen;

    public static RootDisplayBuilder builder(UIManagementSystem uiManagementSystem, DispatcherUIScriptMachine dispatcherUIScriptMachine) {
        return new RootDisplayBuilder(uiManagementSystem, dispatcherUIScriptMachine);
    }

    private RootDisplayBuilder(UIManagementSystem uiManagementSystem, DispatcherUIScriptMachine dispatcherUIScriptMachine) {
        this.uiManagementSystem = uiManagementSystem;
        this.dispatcherUIScriptMachine = dispatcherUIScriptMachine;
    }

    public RootDisplayBuilder availableConstraintNames(String... availableConstraintNames) {
        this.availableConstraintNames = Arrays.asList(availableConstraintNames);
        return this;
    }

    public RootDisplayBuilder isFillScreen(boolean isFillScreen) {
        this.isFillScreen = isFillScreen;
        return this;
    }

    public InnerRootDisplayBuilder build() {
        ComplexConstraintDisplayImpl rootDisplay = (ComplexConstraintDisplayImpl)uiManagementSystem
                .bindDisplay(
                        "complexConstraintFactory",
                        ImmutableMap.of(
                                ComplexConstraintDisplayFactory.AVAILABLE_CONSTRAINT_NAMES, availableConstraintNames,
                                ComplexConstraintDisplayFactory.IS_FILL_SCREEN, isFillScreen
                        )
                );
        rootDisplay.asActor().validate();

        return new InnerRootDisplayBuilder(uiManagementSystem, dispatcherUIScriptMachine, rootDisplay);
    }
}
