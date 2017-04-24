package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

class EndlessRunnerGame implements Screen, GestureDetector.GestureListener
{
    private gameEntry game;
    private ArrayList<GameObject> gameObjects;
    private Texture bg;
    private CustomFont mFont;

    private long time;
    private long timeOffset;
    private long timePaused;
    private long last;
    private Random r;
    private int playerPosition;

    private Music music;
    private int dx = 100;
    private int d = 0;

    EndlessRunnerGame(final gameEntry game)
    {
        this.game = game;
        gameEntry.ENDLESS_RUNNER_SCORE = 0;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        gameObjects = new ArrayList<GameObject>();
        mFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
        bg = new Texture("floortile.png");
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        music = Gdx.audio.newMusic(Gdx.files.internal("sfx/deathcell.ogg"));
        music.play();
        music.setLooping(true);
        r = new Random();
        last = System.currentTimeMillis();
        gameObjects.add(new Player(gameEntry.WIDTH/2,50,0.1f));
        Gdx.input.setInputProcessor(new GestureDetector(this));
        playerPosition = 2;
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        time = System.currentTimeMillis()-timeOffset;
        if(d<-gameEntry.HEIGHT)
        {
            d = 0;
            if(dx<800)
                dx+=10;
        }
        else
            d-=dx*delta;
        game.setCameraBits();
        game.batch.begin();
        game.batch.draw(bg, 0, gameEntry.HEIGHT+d, gameEntry.WIDTH/4, gameEntry.HEIGHT/4);
        game.batch.draw(bg, 0, d, gameEntry.WIDTH, gameEntry.HEIGHT);
        for(GameObject g: gameObjects)
        {
            if(g instanceof Player)
            {
                g.setX(playerPosition*(gameEntry.WIDTH/4));
            }
            g.draw(game.batch,delta);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause()
    {
        timePaused = System.currentTimeMillis();
    }

    @Override
    public void resume() {
        timeOffset += System.currentTimeMillis()-timePaused;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        bg.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button)
    {
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX>0)
            {
               Gdx.app.log("Swipe","Right");
                if(playerPosition<3)
                    playerPosition++;
            }
            else
            {
                Gdx.app.log("Swipe","Left");
                if(playerPosition>1)
                    playerPosition--;
            }
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
