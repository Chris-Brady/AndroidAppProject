package com.industries118.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

class TapImp extends GameObject
{
    //Animation
    private Animation<TextureRegion> idleAnim;
    private Texture idleSheet;
    private float stateTime;

    //GameLogic
    private long popTime;
    private long deathTime;
    private boolean popped = false;
    private boolean bopped = false;
    private Random r;

    TapImp(int x, int y)
    {
        super(x,y);
        setAnim("impspawn.png",16,1,0.066f);
        r = new Random();
        deathTime = System.currentTimeMillis();
    }

    void update(long time)
    {
        if(!popped)
        {
            int random = r.nextInt(1000);
            if((random > 666&& random <690)&&time-deathTime>333)
            {
                popTime = time;
                stateTime = 0f;
                popped = true;
            }
        }
        else
        {
            if((time-popTime)>666)
            {
                deathTime = time;
                popped = false;
            }
        }
    }

    void touchUpdate(Vector3 input)
    {
        if(popped)
            if(input.x > getX()&&input.x < getX()+160)
                if(input.y>getY()&&input.y < getY()+130)
                    kill();
    }

    void draw(SpriteBatch batch,float delta)
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime, false);
        if(popped)
            batch.draw(currentFrame,getX(),getY(),160,130);
    }

    void dispose()
    {
        idleSheet.dispose();
    }

    private void kill()
    {
        Gdx.app.log("Test","Tapped imp: "+getX()+","+getY());
        gameEntry.TAP_AN_IMP_SCORE++;
        popped = false;
    }

    private void setAnim(String name, int cols, int rows,float time)
    {
        idleSheet = new Texture(name);
        TextureRegion[][] tmp = TextureRegion.split(idleSheet,idleSheet.getWidth()/cols,idleSheet.getHeight()/rows);
        TextureRegion[] idleFrames = new TextureRegion[cols*rows];
        int index = 0;
        for(int i = 0;i<rows;i++)
            for(int j = 0;j<cols;j++)
                idleFrames[index++] = tmp[i][j];
        idleAnim = new Animation<TextureRegion>(time,idleFrames);
        stateTime = 0f;
    }
}
