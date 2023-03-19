package com.jgames.survival.ui.cellactorfactories;

import javax.annotation.Nullable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.core.model.GameObject;
import com.jgames.survival.ui.UIException;

public class AlignedLabelFactory implements CellActorFactory {
    private final String labelText;
    private final int align;
    private final Color color;

    public AlignedLabelFactory(String labelText, int align) {
        this(labelText, align, Color.BLACK);
    }

    public AlignedLabelFactory(String labelText, int align, Color color) {
        this.labelText = labelText;
        this.align = align;
        this.color = color;
    }

    @Override
    public Label create(@Nullable GameObject gameObject) throws UIException {
        Label label = new Label(labelText, createLabelStyle());
        label.setAlignment(align);
        return label;
    }

    private LabelStyle createLabelStyle() {
        LabelStyle style = new LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = color;
        return style;
    }
}
