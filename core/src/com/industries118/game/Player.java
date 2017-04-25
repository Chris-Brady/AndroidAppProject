package com.industries118.game;

class Player extends GameObject
{
    Player(float x, float y, float animSpeed)
    {
        super(x, y);
        super.setAnim("runner.png",8,1, animSpeed);
        setWidth(220);
        setHeight(200);
    }
}
