package com.jgames.survival.view.core.assets;

import static com.jgames.survival.view.core.Constants.DISPLAY_DEFAULT_BACKGROUND;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.reflections.vfs.Vfs;
import org.reflections.vfs.Vfs.Dir;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.utils.Logger;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.jgames.survival.view.core.UIException;
import com.jgames.survival.view.core.UIRuntimeException;

@Bean
public class ScannerResourcesTextureStorage implements TextureStorage {
    private final Map<String, Texture> textureStorage = new HashMap<>();
    private final Map<String, Sprite> spriteStorage = new HashMap<>();

    public ScannerResourcesTextureStorage(Logger logger, List<TexturePostLoader> textureSuppliers,
            List<TextureFilter> textureFilters) throws MalformedURLException
    {
        for (FileHandle resourceHandler : localFiles()) {
            if (needLoadTexture(resourceHandler, textureFilters)) {
                textureStorage.put(resourceHandler.nameWithoutExtension(), new Texture(resourceHandler.path()));
            }
        }

        addAdditionalTextures(logger, textureSuppliers);

        textureStorage.put(DISPLAY_DEFAULT_BACKGROUND, new Texture("default.png"));
    }

    private List<FileHandle> localFiles() throws MalformedURLException {
        if (ApplicationType.Desktop.equals(Gdx.app.getType())) {
            URL resourcePath = getClass().getClassLoader().getResource("gameconfig.properties");
            if (resourcePath == null) {
                throw new UIRuntimeException("Resource [gameconfig.properties] was not found. Assets was not loaded");
            }
            File resourcesRootFile = new File(resourcePath.getFile()).getParentFile();
            URL resourcesRoot = resourcesRootFile.toURI().toURL();
            Dir dir = Vfs.fromURL(resourcesRoot);

            List<FileHandle> files = new ArrayList<>();
            for (Vfs.File file : dir.getFiles()) {
                files.add(new FileHandle(new File(file.getRelativePath())));
            }

            return files;
        } else {
            return Arrays.stream(Gdx.files.getFileHandle("", FileType.Classpath).list()).toList();
        }
    }

    private static boolean needLoadTexture(FileHandle resourceHandler, List<TextureFilter> textureFilters) {
        for (TextureFilter textureSupplier : textureFilters) {
            if (!textureSupplier.needLoadTexture(resourceHandler)) {
                return false;
            }
        }
        return true;
    }

    private void addAdditionalTextures(Logger logger, List<TexturePostLoader> textureSuppliers) {
        for (TexturePostLoader textureSupplier : textureSuppliers) {
            try {
                textureSupplier.configureTextures(textureStorage, spriteStorage);
            } catch (UIException e) {
                logger.error("ScannerResourcesTextureStorage", "Texture was not loaded by [%s]".formatted(
                        textureSupplier), e);
            }
        }
    }

    @Nullable
    @Override
    public Texture getTexture(String textureName) {
        return textureStorage.get(textureName);
    }

    @Nullable
    @Override
    public Sprite createSprite(String spriteName) {
        Texture texture = getTexture(spriteName);
        if (texture == null) {
            Sprite sprite = spriteStorage.get(spriteName);
            return sprite == null ? null : new Sprite(sprite);
        }

        return new Sprite(getTexture(spriteName));
    }

    @Nullable
    @Override
    public NinePatch createNinePatch(String patchName, int boardSize) {
        return createNinePatch(patchName, boardSize, boardSize, boardSize, boardSize);
    }

    @Nullable
    @Override
    public NinePatch createNinePatch(String patchName, int left, int right, int top, int bottom) {
        return new NinePatch(createSprite(patchName), left, right, top, bottom);
    }

    @Override
    public void dispose() {
        textureStorage.values().forEach(Texture::dispose);
    }
}
