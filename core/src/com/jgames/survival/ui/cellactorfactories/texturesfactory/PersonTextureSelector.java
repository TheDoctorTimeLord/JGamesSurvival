package com.jgames.survival.ui.cellactorfactories.texturesfactory;

import java.util.List;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jgames.survival.presenter.filling.gamestate.model.GameObject;
import com.jgames.survival.presenter.filling.gamestate.model.objectcomponents.DirectionComponent;
import com.jgames.survival.ui.UIException;
import com.jgames.survival.ui.cellactorfactories.texturesfactory.TexturesFactory.TextureSelector;

public class PersonTextureSelector implements TextureSelector {
    @Override
    public TextureRegion select(List<TextureRegion> textures, @Nullable GameObject gameObject) {
        if (gameObject == null) {
            throw new UIException("Game object was null but it is required");
        }

        DirectionComponent component = gameObject.getComponent(DirectionComponent.class);
        if (component == null) {
            throw new UIException("Game object [%s] has not component [%s] but it is required".formatted(gameObject, DirectionComponent.class));
        }

        return textures.get(component.getDirection().ordinal());
    }
}
