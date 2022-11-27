package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignRight extends AlignConstraint {
    public static final String NAME = "alignRight";

    public AlignRight() {
        super(NAME, 1, 0, -1, 0);
    }
}