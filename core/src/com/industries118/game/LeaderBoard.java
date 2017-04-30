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

//Screen for entering name and showing score to prepare for upload
class LeaderBoard implements Screen
{
    private GameEntry game;         //Variable to store GameEntry for access
    private CustomFont mFont,sFont; //Custom Fonts for text
    private int score;              //Score to Upload

    private Stage stage;            //LibGdx Stage object to organise buttons and other UI elements
    private TextField tf;           //TextField to enter name
    private Texture bg;             //Background texture

    //Constructor
    LeaderBoard(final GameEntry game, final int score, final int state)
    {
        this.game = game;
        this.score = score;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false, GameEntry.WIDTH, GameEntry.HEIGHT);
        mFont = new CustomFont("BLOODY.TTF",40, Color.YELLOW);
        sFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
        bg = new Texture("star.png");

        //UI
        stage = new Stage(new StretchViewport(GameEntry.WIDTH, GameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));

        tf = new TextField("", skin);
        tf.setSize(300,40);
        tf.setPosition((GameEntry.WIDTH/2-(tf.getWidth()/2)), GameEntry.HEIGHT/2);
        stage.setKeyboardFocus(tf);
        tf.getOnscreenKeyboard().show(true);

        TextButton tb = new TextButton("Upload Score!", skin);
        tb.setSize(300,30);
        tb.setPosition((GameEntry.WIDTH/2-(tb.getWidth()/2)), GameEntry.HEIGHT/2-(tf.getHeight()*2));
        //Button to submit and check name in TextField
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                if(tf.getText().length()>2&&tf.getText().length()<17)
                {
                    tf.getOnscreenKeyboard().show(false);
                    game.setScreen(new LeaderboardDisplay(game,score,tf.getText(),state));
                    dispose();
                }
                else
                {
                    //Toast usage example
                    game.ar.toast("Between three and sixteen characters please!");
                }
            }
        });
        stage.addActor(tb);
        stage.addActor(tf);
    }

    //Called 30 or 60 times every second
    @Override
    public void render(float delta)
    {
        game.setCameraBits();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(bg,0,0, GameEntry.WIDTH, GameEntry.HEIGHT);
        mFont.draw(stage.getBatch(),"GAME OVER",(GameEntry.WIDTH/2)-(mFont.getWidth()/2),600);
        sFont.draw(stage.getBatch(),"Score: "+score,(GameEntry.WIDTH/2)-(sFont.getWidth()/2),500);
        stage.getBatch().end();
        stage.draw();
    }

    //Called when Screen is switched, disposes all disposables
    @Override
    public void dispose()
    {
        stage.dispose();
        bg.dispose();
        mFont.dispose();
        sFont.dispose();
    }

    @Override
    public void show(){/*Unused implement method*/}

    @Override
    public void resize(int width, int height){/*Unused implement method*/}

    @Override
    public void pause(){/*Unused implement method*/}

    @Override
    public void resume(){/*Unused implement method*/}

    @Override
    public void hide(){/*Unused implement method*/}
}
