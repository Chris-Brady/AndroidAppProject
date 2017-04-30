package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.google.gson.Gson;

//Screen class to fetch and display the leaderboard
class LeaderboardDisplay implements Screen
{
    private GameEntry game;         //Variable to store GameEntry for access
    private boolean complete;       //False if DBManager is not done
    private DBManager downloader;   //DBManager to retrieve data from online Database

    private Stage stage;            //LibGdx Stage Object to organize buttons and other UI elements
    private Table table;            //LibGdx Table to arrange Scores
    private Skin skin;              //LibGdx Skin for UI
    private String[] tableNames = {"TapAnImp","InfiniteRunner"};    //Database table names
    private int state;              //Used to determine which game the user has just played

    //Constructor to display Scores
    LeaderboardDisplay(final GameEntry game)
    {
        this(game,0);
        downloader = new DBManager(tableNames[state]);
        downloader.start();
    }

    //Constructor to initialise stuff
    private LeaderboardDisplay(final GameEntry game, int state)
    {
        this.game = game;
        this.state = state;
        this.complete = false;
        makeStage();
        makeButtons();
    }

    //Constructor to upload and display scores
    LeaderboardDisplay(final GameEntry game, int score, String name, int state)
    {
        this(game, state);
        downloader = new DBManager(name,score,tableNames[state]);
        downloader.start();
    }

    //Called 30 or 60 times a second
    @Override
    public void render(float delta)
    {
        if(!complete&&downloader.getStatus())
        {
            fillStage();
            String result = downloader.getInfo();
            Gson gson = new Gson();
            Score[] scores = gson.fromJson(result, Score[].class);
            for(int i = 0; i< scores.length; i++)
            {
                Label num = new Label(i+1+"",skin);
                Label Name = new Label(scores[i].getName(),skin);
                Label Score = new Label(scores[i].getScore(),skin);
                Label Date = new Label(scores[i].getDate(),skin);
                num.setAlignment(1);
                Name.setAlignment(1);
                Score.setAlignment(1);
                Date.setAlignment(1);
                table.add(num).expandX().fillX().setActorHeight(40);
                table.add(Name).expandX().fillX().setActorHeight(40);
                table.add(Score).expandX().fillX().setActorHeight(40);
                table.add(Date).expandX().fillX().setActorHeight(40);
                table.row();
            }
            complete = true;
        }
        game.setCameraBits();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    //Initialise the stage
    private void makeStage()
    {
        stage = new Stage(new StretchViewport(GameEntry.WIDTH, GameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));
    }

    //Fill stage with Objects
    private void fillStage()
    {
        table = new Table(skin);
        ScrollPane pane = new ScrollPane(table, skin);
        pane.setBounds(0,0, GameEntry.WIDTH, GameEntry.HEIGHT-150);
        pane.setPosition(0,100);
        pane.setColor(0,0,0,1);

        Label num = new Label("#",skin);
        Label Name = new Label("Name",skin);
        Label Score = new Label("Score",skin);
        Label Date = new Label("Date",skin);

        num.setAlignment(1);
        Name.setAlignment(1);
        Score.setAlignment(1);
        Date.setAlignment(1);

        table.add(num).expandX().fillX().setActorHeight(40);
        table.add(Name).expandX().fillX().setActorHeight(40);
        table.add(Score).expandX().fillX().setActorHeight(40);
        table.add(Date).expandX().fillX().setActorHeight(40);
        table.row();

        stage.addActor(pane);
    }

    //Create the buttons
    private void makeButtons()
    {
        TextButton tb = new TextButton("Main Menu", skin);
        tb.setSize(300,30);
        tb.setPosition((GameEntry.WIDTH/2-(tb.getWidth()/2)),50);
        tb.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        final TextButton sb = new TextButton("Switch Leaderboard", skin);
        sb.setSize(300,30);
        sb.setPosition((GameEntry.WIDTH/2-(sb.getWidth()/2)), GameEntry.HEIGHT-50);
        sb.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                state = (state+1)%tableNames.length;
                sb.setText(tableNames[state]);
                downloader = new DBManager(tableNames[state]);
                complete = false;
                downloader.start();
            }
        });
        stage.addActor(tb);
        stage.addActor(sb);
    }

    //Called when Screen is switched, disposes all disposables
    @Override
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
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
