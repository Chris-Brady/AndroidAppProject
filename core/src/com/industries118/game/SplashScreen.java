package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

//Screen to display info about a Game, Also acts as loading screen
class SplashScreen implements Screen
{
    private Texture splash; //Texture to be displayed
    private GameEntry game; //Variable to store GameEntry for access
    private Screen next;    //Screen to be displayed next

    //Constructor
    SplashScreen(String image, Screen goTo, final GameEntry game)
    {
        splash = new Texture(image);
        this.game = game;
        next = goTo;
    }

    //Called 30 or 60 times a second
    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        game.batch.begin();
        game.batch.draw(splash,0,0, GameEntry.WIDTH, GameEntry.HEIGHT);
        game.batch.end();
        if(Gdx.input.isTouched())
        {
            game.setScreen(next);
            dispose();
        }
    }

    //Called when switching screens
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
