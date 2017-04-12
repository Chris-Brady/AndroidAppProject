package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class LeaderBoard implements Screen
{
    private gameEntry game;
    int scoreToAdd;
    private CustomFont mFont,sFont;
    boolean gameOverScreen = true;
    private Texture bg;



    LeaderBoard(final gameEntry game, int score)
    {
        this.game = game;
        scoreToAdd = score;
        game.camera = new OrthographicCamera();
        game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
        mFont = new CustomFont("BLOODY.TTF",40, Color.YELLOW);
        sFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
        bg = new Texture("star.png");
        //d = new DBManager("sql8.freemysqlhosting.net", "sql8168796","YBc1vI7kqn","3306","arcadeTAI");
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.1f,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        if(gameOverScreen)
        {
            game.batch.draw(bg,0,-100,gameEntry.WIDTH,gameEntry.HEIGHT);
            mFont.draw(game.batch,"GAME OVER",(gameEntry.WIDTH/2)-(mFont.getWidth()/2),500);
            sFont.draw(game.batch,"Score: "+scoreToAdd,(gameEntry.WIDTH/2)-(sFont.getWidth()/2),400);
        }
        else
        {

        }
        game.batch.end();
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

    }
}
