package com.jgames.survival.view.impl.factories.buttons;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.jgames.survival.view.core.assets.TextureStorage;
import com.jgames.survival.viewmodel.core.scriptmachine.UIActionDispatcher;
import com.jgames.survival.viewmodel.core.scriptmachine.uiscripts.impl.elements.TouchedActorElement;

@Bean
public class NextPhaseButtonGenerator extends BaseButtonGenerator {
    public static final String BUTTON_NAME = "nextPhaseButton";

    private final UIActionDispatcher actionDispatcher;

    public NextPhaseButtonGenerator(TextureStorage textureStorage, UIActionDispatcher actionDispatcher) {
        super(textureStorage, BUTTON_NAME, "Next phase");
        this.actionDispatcher = actionDispatcher;
    }

    @Override
    protected void configureButton(ImageTextButton button) {
        button.setWidth(80);
        button.setHeight(60);
        button.addListener(TouchedActorElement.TOUCHED_ACTOR_LISTENER.apply(actionDispatcher));

        button.setDisabled(true);
    }
}
