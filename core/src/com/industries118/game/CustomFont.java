package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

class CustomFont
{
    private FreeTypeFontGenerator generator;
    private BitmapFont font;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private GlyphLayout glyphLayout;

    CustomFont(String fontName, int fontSize, Color color)
    {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+fontName));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;
        font = generator.generateFont(parameter);
        glyphLayout = new GlyphLayout();
    }

    void draw(Batch batch, String text, int x, int y)
    {
        glyphLayout.setText(font,text);
        font.draw(batch,glyphLayout,x,y);
    }

    void dispose()
    {
        generator.dispose();
        font.dispose();
    }

    public BitmapFont getFont()
    {
        return font;
    }

    int getWidth()
    {
        return (int)glyphLayout.width;
    }
}
