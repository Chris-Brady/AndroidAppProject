package com.industries118.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Game class is instantiated by AndroidLauncher
class GameEntry extends Game
{
    SpriteBatch batch;                             //SpriteBatch is used to draw Objects
    OrthographicCamera camera;                     //Camera is used to setup a view into the world
    static int TAP_AN_IMP_SCORE;                   //Score for Tap an Imp Game
    static float ENDLESS_RUNNER_SCORE;             //Score for Endless Runner Game
    static final int WIDTH = 400, HEIGHT = 800;    //Constants for view Width and Height
    ActionResolver ar;                             //ActionResolver Object to interface with Android features

    //Constructor
    GameEntry(ActionResolver ar){this.ar = ar;}

    //Called when Object is instantiated
    @Override
    public void create()
    {
        batch = new SpriteBatch();
        this.setScreen(new MainMenu(this));
    }

    //Called 30 or 60 times a second, uses superclasses render() which calls the render() function in it's current Screen Object
    public void render(){super.render();}

    //Called when Object is destroyed (Exiting the app)
    public void dispose(){batch.dispose();}

    //Function used to set up basic camera settings, called in almost every Screen to improve efficiency by reducing Camera Object creation
    void setCameraBits()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
