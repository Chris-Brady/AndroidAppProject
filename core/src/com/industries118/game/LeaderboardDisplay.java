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

class LeaderboardDisplay implements Screen
{
    private gameEntry game;
    private boolean complete;
    private DBManager downloader;

    //UI
    private Stage stage;
    private Table table;
    private Skin skin;


    LeaderboardDisplay(final gameEntry game, int score,String tableName,String name)
    {
        this.game = game;
        this.complete = false;
        downloader = new DBManager(name,score,tableName);
        downloader.start();

        stage = new Stage(new StretchViewport(gameEntry.WIDTH,gameEntry.HEIGHT,game.camera));
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("layouts/uiskin.json"));
        table = new Table(skin);
        ScrollPane pane = new ScrollPane(table, skin);
        pane.setBounds(0,0,gameEntry.WIDTH,gameEntry.HEIGHT-150);
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

        TextButton tb = new TextButton("Main Menu", skin);
        tb.setSize(300,30);
        tb.setPosition((gameEntry.WIDTH/2-(tb.getWidth()/2)),50);
        tb.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent e, float x, float y)
            {
                    game.setScreen(new MainMenu(game));
                    dispose();
            }
        });
        stage.addActor(tb);
        stage.addActor(pane);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if(downloader.getStatus())
            if (!complete)
            {
                String result = downloader.getInfo();
                complete = true;
                Gson gson = new Gson();
                Score[] scores = gson.fromJson(result, Score[].class);
                for(int i = 0; i< scores.length; i++)
                {
                    Label num = new Label(i+"",skin);
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
            }
        game.setCameraBits();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        skin.dispose();
    }
}
