package com.jgames.survival.view.core.actions;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.utils.pubsub.Publisher;

@Bean
public class UIActionManager extends Publisher<UIAction, UIActionListener> {
}
