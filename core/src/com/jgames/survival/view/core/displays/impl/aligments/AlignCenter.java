package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignCenter extends AlignConstraint {
    public static final String NAME = "alignCenter";

    public AlignCenter() {
        super(NAME, 0, 0, 0, 0);
    }
}
