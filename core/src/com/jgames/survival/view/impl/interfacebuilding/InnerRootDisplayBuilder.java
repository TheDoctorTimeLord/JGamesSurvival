package com.jgames.survival.view.impl.interfacebuilding;

import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.ComplexConstraintDisplay;
import com.jgames.survival.viewmodel.core.scriptmachine.DispatcherUIScriptMachine;

public class InnerRootDisplayBuilder {
    private final UIManagementSystem uiManagementSystem;
    private final DispatcherUIScriptMachine scriptMachine;
    private final ComplexConstraintDisplay rootDisplay;

    InnerRootDisplayBuilder(UIManagementSystem uiManagementSystem, DispatcherUIScriptMachine scriptMachine,
            ComplexConstraintDisplay rootDisplay)
    {
        this.uiManagementSystem = uiManagementSystem;
        this.scriptMachine = scriptMachine;
        this.rootDisplay = rootDisplay;
    }

    public InnerRootDisplayBuilder createScript(ScriptBuilder<ComplexConstraintDisplay> builder) {
        builder.build(rootDisplay, uiManagementSystem, scriptMachine);
        return this;
    }
}
