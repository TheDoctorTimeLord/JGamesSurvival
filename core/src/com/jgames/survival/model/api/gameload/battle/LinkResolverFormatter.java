package com.jgames.survival.model.api.gameload.battle;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ru.jengine.beancontainer.annotations.Bean;
import ru.jengine.jsonconverter.jsonformatting.formatters.BaseGsonFormatter;
import ru.jengine.jsonconverter.utils.JsonUtils;

import com.google.gson.JsonObject;

@Bean
public class LinkResolverFormatter extends BaseGsonFormatter {
    @Override
    public List<String> getHandledTypes() {
        return Collections.emptyList();
    }

    @Override
    public List<String> extractJsonDependencies(JsonObject jsonObject) {
        return jsonObject.entrySet().stream()
                .filter(entry -> JsonUtils.isLink(entry.getValue()))
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}
