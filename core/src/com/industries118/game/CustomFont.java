package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

//Custom class created to allow me to more easily implement .ttf fonts as bitmap fonts
class CustomFont
{
    private FreeTypeFontGenerator generator;
    private BitmapFont font;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private GlyphLayout glyphLayout;

    //Constructor
    CustomFont(String fontName, int fontSize, Color color)
    {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+fontName));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = fontSize;
        parameter.color = color;
        font = generator.generateFont(parameter);
        glyphLayout = new GlyphLayout();
    }

    //Draw the font with the given text at the x and y co-ords using the Batch Object
    void draw(Batch batch, String text, int x, int y)
    {
        glyphLayout.setText(font,text);
        font.draw(batch,glyphLayout,x,y);
    }

    //Dispose of all disposables to avoid memory leaks
    void dispose()
    {
        generator.dispose();
        font.dispose();
    }

    //Return the generated bitmap font
    public BitmapFont getFont()
    {
        return font;
    }

    //Return the width of the generated font object
    int getWidth()
    {
        return (int)glyphLayout.width;
    }
}
