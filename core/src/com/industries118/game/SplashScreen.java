package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

class SplashScreen implements Screen
{
    private Texture splash;
    private gameEntry game;
    private Screen next;

    SplashScreen(String image, Screen goTo, final gameEntry game)
    {
        splash = new Texture(image);
        this.game = game;
        next = goTo;
    }

    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        game.batch.begin();
        game.batch.draw(splash,0,0,gameEntry.WIDTH,gameEntry.HEIGHT);
        game.batch.end();
        if(Gdx.input.isTouched())
        {
            game.setScreen(next);
            dispose();
        }
    }

    @Override
    public void dispose(){splash.dispose();}

    @Override
    public void show(){/*Unused implement method*/}

    @Override
    public void resize(int width, int height) {/*Unused implement method*/}

    @Override
    public void pause(){/*Unused implement method*/}

    @Override
    public void resume(){/*Unused implement method*/}

    @Override
    public void hide(){/*Unused implement method*/}
}
