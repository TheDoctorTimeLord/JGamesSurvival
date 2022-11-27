package com.jgames.survival.view.core.displays.impl.aligments;

import ru.jengine.beancontainer.annotations.Bean;

import com.jgames.survival.view.core.displays.impl.AlignConstraint;

@Bean
public class AlignBottomRight extends AlignConstraint {
    public static final String NAME = "alignBottomRight";

    public AlignBottomRight() {
        super(NAME, 1, -1, -1, 1);
    }
}
