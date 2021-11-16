package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

public class TextListWidget extends Table {
    private LabelStyle labelStyle = new LabelStyle();
    private final Container<ScrollPane> frontView;

    public TextListWidget(NinePatch background, String widgetName) {
        align(Align.center | Align.top);
        setName(widgetName);

        ScrollPane scrollPane = new ScrollPane(this);
        scrollPane.setOverscroll(false, false);

        frontView = new Container<>(scrollPane);
        frontView.setBackground(new NinePatchDrawable(background));
    }

    public void addText(String text) {
        add(new Label(text, labelStyle)).pad(4).row();
    }

    public void setLabelStyle(LabelStyle style) {
        labelStyle = style;
    }

    public Container<?> getFrontWrapper() {
        return frontView;
    }
}
