package com.jgames.survival.presenter.filling.jsonloading;

import ru.jengine.jsonconverter.exceptions.ResourceLoadingException;
import ru.jengine.jsonconverter.resources.ResourceLoader;
import ru.jengine.jsonconverter.resources.ResourceMetadata;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.jgames.survival.model.api.gameload.JsonApiConstants.ResourceNamespaces;

public class CoreResourceLoader implements ResourceLoader {
    @Override
    public String getResource(ResourceMetadata metadata) throws ResourceLoadingException {
        if (!ResourceNamespaces.CORE_RESOURCES.equals(metadata.getNamespace())) {
            throw new ResourceLoadingException("Loading from none [core] namespace is not supporting");
        }

        String path = metadata.getObjectType() != null
                ? "%s/%s.json".formatted(metadata.getObjectType(), metadata.getPath())
                : "%s.json".formatted(metadata.getPath());

        FileHandle local = Gdx.files.internal(path);
        if (!local.exists()) {
            throw new ResourceLoadingException("JSON by path [%s] is not found".formatted(path));
        }

        return local.readString();
    }

    @Override
    public void addResourceToCache(ResourceMetadata metadata, String resource) { }

    @Override
    public void cleanCache() { }
}
