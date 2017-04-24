package com.industries118.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject
{
    private float animSpeed;

    Player(float x, float y, float animSpeed)
    {
        super(x, y);
        this.animSpeed = animSpeed;
        super.setAnim("runner.png",8,1,this.animSpeed);
    }

    @Override
    void draw(SpriteBatch batch, float delta)
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime, true);
        batch.draw(currentFrame,getX()-110,getY()-100,220,200);
    }

    public void setAnimSpeed(float speed)
    {
        this.animSpeed = speed;
    }

}
