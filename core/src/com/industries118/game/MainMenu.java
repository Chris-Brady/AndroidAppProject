package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu implements Screen
{
    final gameEntry game;
    private CustomFont mFont, sFont;
    private Texture bg;

    public MainMenu(final gameEntry game)
    {
        this.game = game;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        mFont = new CustomFont("BLOODY.TTF",40, Color.YELLOW);
        sFont = new CustomFont("BLOODY.TTF",20, Color.YELLOW);
        bg = new Texture("boomarcade.png");
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        game.batch.begin();
        game.batch.draw(bg,0,0,gameEntry.WIDTH,gameEntry.HEIGHT);
        sFont.draw(game.batch,"Tap to play!",(gameEntry.WIDTH/2)-(sFont.getWidth()/2),300);
        game.batch.end();
        if(Gdx.input.isTouched())
        {
            game.setScreen(new SplashScreen("taisplash.png",new TapImpGame(game),game));
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
        bg.dispose();
    }
}
