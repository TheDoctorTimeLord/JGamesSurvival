package com.jgames.survival.view.core.factories.impl;

import java.util.function.Supplier;

import com.badlogic.gdx.scenes.scene2d.ui.Button;

public interface ButtonGenerator {
    ButtonConfiguration generate();

    class ButtonConfiguration {
        public final String buttonName;
        public final Supplier<Button> buttonGenerator;

        public ButtonConfiguration(String buttonName, Supplier<Button> buttonGenerator) {
            this.buttonName = buttonName;
            this.buttonGenerator = buttonGenerator;
        }
    }
}
