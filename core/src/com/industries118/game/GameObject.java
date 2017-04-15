package com.industries118.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class GameObject
{
    private int x,y;

    GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    void update(long time)
    {

    }

    void draw(SpriteBatch batch, float delta)
    {

    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

    void setX(int x)
    {
        this.x = x;
    }

    void setY(int y)
    {
        this.y = y;
    }

    void dispose()
    {

    }
}
