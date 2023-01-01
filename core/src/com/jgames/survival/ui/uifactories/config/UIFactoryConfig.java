package com.jgames.survival.ui.uifactories.config;

public class UIFactoryConfig {
    private PhaseAndTurnConfig phaseAndTurn = new PhaseAndTurnConfig();
    private SaveAndLoadConfig saveAndLoad = new SaveAndLoadConfig();

    public PhaseAndTurnConfig getPhaseAndTurn() {
        return phaseAndTurn;
    }

    public SaveAndLoadConfig getSaveAndLoad() {
        return saveAndLoad;
    }
}
