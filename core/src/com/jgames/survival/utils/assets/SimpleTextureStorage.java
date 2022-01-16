package com.jgames.survival.utils.assets;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SimpleTextureStorage implements TextureStorage {
    private static final int BOARD_SIZE = 4;

    private Texture boardingTexture;
    private Texture[] personDirections;

    private Map<String, Sprite> createdSprites = new HashMap<>();

    @Override
    public TextureStorage load(TextureStorageConfiguration configuration) {
        boardingTexture = new Texture("cell.png");

        personDirections = new Texture[] {
                new Texture("personUp.png"),
                new Texture("personRight.png"),
                new Texture("personDown.png"),
                new Texture("personLeft.png")
        };

        createdSprites.put(Constants.COMMON, new Sprite(boardingTexture));
        createdSprites.put(Constants.PERSON_UP, new Sprite(personDirections[0]));
        createdSprites.put(Constants.PERSON_RIGHT, new Sprite(personDirections[1]));
        createdSprites.put(Constants.PERSON_DOWN, new Sprite(personDirections[2]));
        createdSprites.put(Constants.PERSON_LEFT, new Sprite(personDirections[3]));

        return this;
    }

    @Override
    public Sprite createSprite(String spriteName) {
        Sprite result = createdSprites.get(spriteName);
        if (result == null) {
            throw new AssetException("Sprite with name [" + spriteName + "] did not found");
        }
        return result;
    }

    @Override
    public NinePatch createNinePatch(String patchName) {
        Sprite sprite = createSprite(patchName);
        return new NinePatch(sprite, BOARD_SIZE, BOARD_SIZE, BOARD_SIZE, BOARD_SIZE);
    }

    @Override
    public void dispose() {
        boardingTexture.dispose();

        for (Texture texture : personDirections) {
            texture.dispose();
        }
    }

    public interface Constants {
        String COMMON = "common";
        String PERSON_UP = "personUp";
        String PERSON_RIGHT = "personRight";
        String PERSON_DOWN = "personDown";
        String PERSON_LEFT = "personLeft";
    }
}
