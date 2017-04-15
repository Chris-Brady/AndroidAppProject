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

class MainMenu implements Screen
{
    final gameEntry game;
    private Texture bg;
    private Music music;
    private Stage stage;

    MainMenu(final gameEntry game)
    {
        this.game = game;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        music = Gdx.audio.newMusic(Gdx.files.internal("sfx/boomrock.ogg"));
        music.play();
        music.setLooping(true);
        bg = new Texture("boomarcade.png");

        stage = new Stage(new StretchViewport(gameEntry.WIDTH,gameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));

        TextButton tb1 = new TextButton("Tap An Imp!", skin);
        tb1.setSize(300,30);
        tb1.setPosition((gameEntry.WIDTH/2-(tb1.getWidth()/2)),(gameEntry.HEIGHT/2)-60);
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
        tb2.setPosition((gameEntry.WIDTH/2-(tb2.getWidth()/2)),(gameEntry.HEIGHT/2)-120);
        tb2.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                processInput(2);
            }
        });

        stage.addActor(tb1);
        stage.addActor(tb2);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0,gameEntry.WIDTH,gameEntry.HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {
        music.pause();
    }

    @Override
    public void resume()
    {
        music.play();
    }

    @Override
    public void hide()
    {
        music.stop();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        music.dispose();
        bg.dispose();
    }

    private void processInput(int id)
    {
        music.stop();
        switch (id)
        {
            case 1:
                game.setScreen(new SplashScreen("taisplash.png",new TapImpGame(game),game));
                break;
            case 2:
                game.setScreen(new SplashScreen("taisplash.png",new EndlessRunnerGame(game),game));
                break;
        }
        dispose();
    }
}
