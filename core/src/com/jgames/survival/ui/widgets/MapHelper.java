package com.jgames.survival.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class MapHelper {
    public static void makeCellEmpty(MapCell cell) {
        cell
            .clearCell()
            .addPart(createCellFilling(cell.getDefaultTexture()));
    }

    public static MapCellFillingBuilder createCellFilling(MapCell cell, boolean needClear) {
        return new MapCellFillingBuilder(cell, needClear);
    }

    public static class MapCellFillingBuilder {
        private final MapCell cell;
        private final boolean needClear;
        private ImageButton mainTexture;
        private Label hpLabel;
        private Image tint;

        public MapCellFillingBuilder(MapCell cell, boolean needClear) {
            this.cell = cell;
            this.needClear = needClear;
        }

        public MapCellFillingBuilder setMainTexture(TextureRegion sprite) {
            mainTexture = createCellFilling(sprite);
            return this;
        }

        public MapCellFillingBuilder setHpLabel(int hp) {
            hpLabel = createHpLabel(hp);
            return this;
        }

        public MapCellFillingBuilder setTint(TextureRegion sprite) {
            tint = new Image(new TextureRegionDrawable(sprite).tint(new Color(0.9f, 0.9f, 0.9f, 0.5f)));
            return this;
        }

        public void build() {
            if (needClear) {
                cell.clearCell();
            }
            if (mainTexture != null) {
                cell.addPart(mainTexture);
            }
            if (hpLabel != null) {
                cell.addPart(hpLabel);
            }
            if (tint != null) {
                cell.addPart(tint);
            }
        }
    }

    private static ImageButton createCellFilling(TextureRegion sprite) {
        return new ImageButton(createMapCellTextureStyle(sprite));
    }

    private static ImageButtonStyle createMapCellTextureStyle(TextureRegion sprite) {
        ImageButtonStyle style = new ImageButtonStyle();
        style.up = new TextureRegionDrawable(sprite);
        style.down = new TextureRegionDrawable(sprite).tint(new Color(0.9f, 0.9f, 0.9f, 1f));
        return style;
    }

    private static Label createHpLabel(int hp) {
        Label hpLabel = new Label(hp + "  ", createHpTextStyle());
        hpLabel.setAlignment(Align.topRight);
        return hpLabel;
    }

    private static LabelStyle createHpTextStyle() {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = new Color(0.8f, 0.2f, 0.2f, 1f);
        return labelStyle;
    }
}
