package com.industries118.game;
//Player Object
class Player extends GameObject
{
    //Constructor
    Player(float x, float y, float animSpeed)
    {
        super(x, y);
        super.setAnim("runner.png",8,1, animSpeed);
        setWidth(220);
        setHeight(200);
    }
}
