package com.jgames.survival.ui.assets;

import java.util.HashMap;
import java.util.Map;

import ru.jengine.beancontainer.annotations.Bean;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

@Bean
public class SimpleTextureStorage implements TextureStorage {
    private static final int BOARD_SIZE = 4;

    private final Texture boardingTexture;
    private final Texture cellBackground;
    private final Texture deadBody;
    private final Texture wall;
    private final Texture[] personDirections;
    private final Map<String, Sprite> createdSprites = new HashMap<>();

    public SimpleTextureStorage() {
        boardingTexture = new Texture("cell.png");

        cellBackground = new Texture("celldr.png");
        deadBody = new Texture("deadbody.png");
        wall = new Texture("wall.png");

        personDirections = new Texture[] {
                new Texture("personUpdr.png"),
                new Texture("personRightdr.png"),
                new Texture("personDowndr.png")
        };

        createdSprites.put(Constants.DEFAULT, new Sprite(boardingTexture));
        createdSprites.put(Constants.COMMON, new Sprite(boardingTexture));
        createdSprites.put(Constants.CELL_BACKGROUND, new Sprite(cellBackground));
        createdSprites.put(Constants.WALL, new Sprite(wall));
        createdSprites.put(Constants.DEAD_BODY, new Sprite(deadBody));
        createdSprites.put(Constants.PERSON_UP, new Sprite(personDirections[0]));
        createdSprites.put(Constants.PERSON_RIGHT, new Sprite(personDirections[1]));
        createdSprites.put(Constants.PERSON_DOWN, new Sprite(personDirections[2]));
        Sprite personLeft = new Sprite(personDirections[1]);
        personLeft.setFlip(true, false);
        createdSprites.put(Constants.PERSON_LEFT, personLeft);
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
        cellBackground.dispose();
        deadBody.dispose();
        wall.dispose();

        for (Texture texture : personDirections) {
            texture.dispose();
        }
    }

    public interface Constants {
        String COMMON = "common";
        String CELL_BACKGROUND = "cellBackground";
        String WALL = "wall";
        String DEAD_BODY = "deadBody";
        String PERSON_UP = "personUp";
        String PERSON_RIGHT = "personRight";
        String PERSON_DOWN = "personDown";
        String PERSON_LEFT = "personLeft";
        String DEFAULT = "default";
    }
}
