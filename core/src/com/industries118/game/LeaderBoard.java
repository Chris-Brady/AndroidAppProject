package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class LeaderBoard implements Screen
{
    private gameEntry game;
    private CustomFont mFont,sFont;
    private int score;
    //UI
    private Stage stage;
    private TextField tf;
    private TextButton tb;
    private Texture bg;

    LeaderBoard(final gameEntry game, final int score, final String tableName)
    {
        this.game = game;
        this.score = score;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        mFont = new CustomFont("BLOODY.TTF",40, Color.YELLOW);
        sFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
        bg = new Texture("star.png");

        //UI
        stage = new Stage(new StretchViewport(gameEntry.WIDTH,gameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));

        tf = new TextField("", skin);
        tf.setSize(300,40);
        tf.setPosition((gameEntry.WIDTH/2-(tf.getWidth()/2)),gameEntry.HEIGHT/2);
        stage.setKeyboardFocus(tf);
        tf.getOnscreenKeyboard().show(true);

        tb = new TextButton("Upload Score!", skin);
        tb.setSize(300,30);
        tb.setPosition((gameEntry.WIDTH/2-(tb.getWidth()/2)),gameEntry.HEIGHT/2-(tf.getHeight()*2));
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                if(tf.getText().length()>2)
                {
                    tf.getOnscreenKeyboard().show(false);
                    game.setScreen(new LeaderboardDisplay(game,score,tableName,tf.getText()));
                    dispose();
                }
                else
                {
                    game.ar.toast("At least three characters please!");
                }
            }
        });

        stage.addActor(tb);
        stage.addActor(tf);
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
        mFont.draw(stage.getBatch(),"GAME OVER",(gameEntry.WIDTH/2)-(mFont.getWidth()/2),600);
        sFont.draw(stage.getBatch(),"Score: "+score,(gameEntry.WIDTH/2)-(sFont.getWidth()/2),500);
        stage.getBatch().end();
        stage.draw();


        /*game.batch.begin();
        if(gameOverScreen)
        {
            game.batch.draw(bg,0,0,gameEntry.WIDTH,gameEntry.HEIGHT);
            mFont.draw(game.batch,"GAME OVER",(gameEntry.WIDTH/2)-(mFont.getWidth()/2),600);
            sFont.draw(game.batch,"Score: "+scoreToAdd,(gameEntry.WIDTH/2)-(sFont.getWidth()/2),500);
        }
        game.batch.end();*/

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
        stage.dispose();
        bg.dispose();
        mFont.dispose();
        sFont.dispose();
    }
}
