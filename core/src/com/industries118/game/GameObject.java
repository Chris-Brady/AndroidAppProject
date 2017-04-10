package com.industries118.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObject
{
    private int x,y;

    GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void update(float delta)
    {

    }

    public void draw(SpriteBatch batch, float delta)
    {

    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

}
