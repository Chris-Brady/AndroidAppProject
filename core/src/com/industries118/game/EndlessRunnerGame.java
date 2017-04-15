package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

class EndlessRunnerGame implements Screen
{
    private gameEntry game;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> garbage;
    private Vector3 touchInput;
    private Texture bg;
    private CustomFont mFont;

    private long time;
    private long timeOffset;
    private long timePaused;
    private long last;
    private Random r;

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
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        time = System.currentTimeMillis()-timeOffset;
        if(time-last>=3000)
        {
            last = time;
            //int place = r.nextInt(3);
            gameObjects.add(new TapImp(0,gameEntry.HEIGHT));
        }
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
        game.batch.draw(bg, 0, gameEntry.HEIGHT+d, gameEntry.WIDTH, gameEntry.HEIGHT);
        game.batch.draw(bg, 0, d, gameEntry.WIDTH, gameEntry.HEIGHT);
        for (GameObject g : gameObjects)
        {
            if(g instanceof TapImp)
            {
                g.setY((int)(g.getY()-(dx*delta)));
                ((TapImp) g).setPopped(true);
                if(g.getY()<-100)
                {

                }
            }
            g.draw(game.batch, delta);
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
}
