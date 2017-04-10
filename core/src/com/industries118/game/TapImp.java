package com.industries118.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TapImp extends GameObject
{
    //Animation
    private static final int COLS = 8, ROWS = 1;
    private Animation<TextureRegion> idleAnim;
    private Texture idleSheet;
    private float stateTime;

    //GameLogic
    private float popTime;
    private boolean popped;

    TapImp(int x, int y)
    {
        super(x,y);
        idleSheet = new Texture("idleimp.png");
        TextureRegion[][] tmp = TextureRegion.split(idleSheet,idleSheet.getWidth()/COLS,idleSheet.getHeight()/ROWS);
        TextureRegion[] idleFrames = new TextureRegion[COLS*ROWS];
        int index = 0;
        for(int i = 0;i<ROWS;i++)
            for(int j = 0;j<COLS;j++)
                idleFrames[index++] = tmp[i][j];
        idleAnim = new Animation<TextureRegion>(0.05f,idleFrames);
        stateTime = 0f;
    }

    public void update(float delta)
    {
        //System.out.println(getX()+","+getY());
    }

    public void draw(SpriteBatch batch,float delta)
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime,true);
        batch.draw(currentFrame,getX(),getY(),160,130);
    }

    public void dispose()
    {
        idleSheet.dispose();
    }
}
