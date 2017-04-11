package com.industries118.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class gameEntry extends Game
{

    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;
    static int TAP_AN_IMP_SCORE;

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenu(this));
    }

    public void render()
    {
        super.render();
    }

    public void dispose()
    {
        batch.dispose();
        font.dispose();
    }
}
