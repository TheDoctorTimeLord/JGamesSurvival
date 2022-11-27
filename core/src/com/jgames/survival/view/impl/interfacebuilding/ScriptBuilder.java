package com.jgames.survival.view.impl.interfacebuilding;

import com.jgames.survival.view.core.UIManagementSystem;
import com.jgames.survival.view.core.displays.Display;
import com.jgames.survival.viewmodel.core.scriptmachine.DispatcherUIScriptMachine;

@FunctionalInterface
public interface ScriptBuilder<D extends Display> {
    void build(D display, UIManagementSystem uiSystem, DispatcherUIScriptMachine scriptMachine);
}
