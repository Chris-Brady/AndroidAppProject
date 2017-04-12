package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenu implements Screen
{
    final gameEntry game;
    private CustomFont mFont, sFont;

    public MainMenu(final gameEntry game)
    {
        this.game = game;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        mFont = new CustomFont("BLOODY.TTF",40, Color.YELLOW);
        sFont = new CustomFont("BLOODY.TTF",20, Color.YELLOW);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.5f,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        mFont.draw(game.batch,"ARCADE",(gameEntry.WIDTH/2)-(mFont.getWidth()/2),500);
        sFont.draw(game.batch,"Tap to play!",(gameEntry.WIDTH/2)-(sFont.getWidth()/2),450);
        game.batch.end();
        if(Gdx.input.isTouched())
        {
            game.setScreen(new TapImpGame(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        mFont.dispose();
        sFont.dispose();
    }
}
