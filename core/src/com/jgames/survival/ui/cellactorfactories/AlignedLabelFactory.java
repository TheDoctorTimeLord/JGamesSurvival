package com.jgames.survival.ui.cellactorfactories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.jgames.survival.presenter.core.CellActorFactory;
import com.jgames.survival.presenter.filling.gamestate.model.DrawingContext;

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
    public Actor create(DrawingContext drawingContext) {
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
