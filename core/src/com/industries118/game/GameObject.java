package com.industries118.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class GameObject
{
    private float x,y;
    private float height, width;
    Animation<TextureRegion> idleAnim;
    Texture idleSheet;
    float stateTime;


    GameObject(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    void update(long time)
    {

    }

    void draw(SpriteBatch batch, float delta)
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime, true);
        batch.draw(currentFrame,getX()-width/2,getY()-height/2,width,height);
    }

    float getX()
    {
        return x;
    }

    float getY()
    {
        return y;
    }

    void setX(float x)
    {
        this.x = x;
    }

    void setY(float y)
    {
        this.y = y;
    }

    public float getHeight() {return height;}

    public void setHeight(float length) {this.height = length;}

    public float getWidth() {return width;}

    public void setWidth(float width) {this.width = width;}


    void setAnim(String name, int cols, int rows,float time)
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

    void dispose()
    {
        idleSheet.dispose();
    }
}
