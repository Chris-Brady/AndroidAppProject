package com.industries118.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class gameEntry extends Game
{

    SpriteBatch batch;
    OrthographicCamera camera;
    static int TAP_AN_IMP_SCORE;
    static int ENDLESS_RUNNER_SCORE;
    static final int WIDTH = 400, HEIGHT = 800;
    ActionResolver ar;

    gameEntry(ActionResolver ar)
    {
        this.ar = ar;
    }

    @Override
    public void create()
    {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }

    public void render()
    {
        super.render();
    }

    public void dispose()
    {
        batch.dispose();
    }

    void setCameraBits()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
