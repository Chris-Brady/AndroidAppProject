package com.industries118.game;

class Fireball extends GameObject
{
    Fireball(float x, float y)
    {
        super(x, y);
        super.setAnim("fireball.png",8,1, 0.1f);
        setWidth(100);
        setHeight(100);
    }
}
