package com.industries118.game;

import com.badlogic.gdx.Screen;

public class LeaderBoard implements Screen
{
    private gameEntry game;
    int scoreToAdd;
    private DBManager d;
    LeaderBoard(final gameEntry game, int score)
    {
        this.game = game;
        scoreToAdd = score;
        d = new DBManager("sql8.freemysqlhosting.net", "sql8168796","YBc1vI7kqn","3306","arcadeTAI");
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {

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
