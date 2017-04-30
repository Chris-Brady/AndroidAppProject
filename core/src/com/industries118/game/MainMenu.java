package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

//Main Menu Screen, Used to access the rest of the app
class MainMenu implements Screen
{
    final GameEntry game;   //Variable to store GameEntry for access
    private Texture bg;     //Background Texture
    private Music music;    //LibGdx Music Object
    private Stage stage;    //Stage to organize UI elements

    //Constructor
    MainMenu(final GameEntry game)
    {
        this.game = game;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, GameEntry.WIDTH, GameEntry.HEIGHT);
        music = Gdx.audio.newMusic(Gdx.files.internal("sfx/boomrock.ogg"));
        music.play();
        music.setLooping(true);
        bg = new Texture("boomarcade.png");

        stage = new Stage(new StretchViewport(GameEntry.WIDTH, GameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));

        TextButton tb1 = new TextButton("Tap An Imp!", skin);
        tb1.setSize(300,30);
        tb1.setPosition((GameEntry.WIDTH/2-(tb1.getWidth()/2)),(GameEntry.HEIGHT/2)-60);
        tb1.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                processInput(1);
            }
        });

        TextButton tb2 = new TextButton("Hell Runner!", skin);
        tb2.setSize(300,30);
        tb2.setPosition((GameEntry.WIDTH/2-(tb2.getWidth()/2)),(GameEntry.HEIGHT/2)-120);
        tb2.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                processInput(2);
            }
        });

        TextButton tb3 = new TextButton("Leaderboard", skin);
        tb3.setSize(300,30);
        tb3.setPosition((GameEntry.WIDTH/2-(tb3.getWidth()/2)),(GameEntry.HEIGHT/2)-180);
        tb3.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                processInput(3);
            }
        });

        stage.addActor(tb1);
        stage.addActor(tb2);
        stage.addActor(tb3);
    }


    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0, GameEntry.WIDTH, GameEntry.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void pause()
    {
        music.pause();
        game.ar.randomNotification();
    }

    //Called after Screen comes into focus
    @Override
    public void resume(){music.play();}

    //Called before screen goes out of view
    @Override
    public void hide(){music.stop();}

    //Called when Switching screens, disposes of all disposable Objects
    @Override
    public void dispose()
    {
        stage.dispose();
        music.dispose();
        bg.dispose();
    }

    //Process button input
    private void processInput(int id)
    {
        music.stop();
        switch (id)
        {
            case 1:
                game.setScreen(new SplashScreen("taisplash.png",new TapImpGame(game),game));
                break;
            case 2:
                game.setScreen(new SplashScreen("irsplash.png",new EndlessRunnerGame(game),game));
                break;
            case 3:
                game.setScreen(new LeaderboardDisplay(game));
                break;
        }
        dispose();
    }

    @Override
    public void resize(int width, int height){/*Unused Implement Method*/}

    @Override
    public void show(){/*Unused Implement Method*/}
}
