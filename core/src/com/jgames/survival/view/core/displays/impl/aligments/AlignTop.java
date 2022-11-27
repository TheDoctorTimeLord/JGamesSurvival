package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignTop extends AlignConstraint {
    public static final String NAME = "alignTop";

    public AlignTop() {
        super(NAME, 0, 1, 0, -1);
    }
}
