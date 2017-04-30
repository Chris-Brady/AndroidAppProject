package com.industries118.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//GameObject class to help with managing different game Objects with similar functionality, Render, Position, Etc
class GameObject
{
    private float x,y;                  //Objects 2D coordinates
    private float height, width;        //Objects width and height
    Animation<TextureRegion> idleAnim;  //Animation Object
    Texture idleSheet;                  //Texture sheet for Animation
    float stateTime;                    //Animation State

    //Constructor
    GameObject(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    //Used by TapImps to update their status
    void update(long time)
    {
        //Overridden by classes that need it
    }

    //Draw method for Object, will draw basic animation sheet, unless overridden
    void draw(SpriteBatch batch, float delta)
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime, true);
        batch.draw(currentFrame,getX()-width/2,getY()-height/2,width,height);
    }

    //Return X Coordinates
    float getX(){return x;}

    //Return Y Coordinates
    float getY(){return y;}

    //Set X Coordinates
    void setX(float x){this.x = x;}

    //Set Y Coordinates
    void setY(float y)
    {
        this.y = y;
    }

    //Get Height
    public float getHeight() {return height;}

    //Set Height
    public void setHeight(float length) {this.height = length;}

    //Get Width
    public float getWidth() {return width;}

    //Get Height
    public void setWidth(float width) {this.width = width;}

    //Set up Animation Frames
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

    //Dispose of all disposable Objects
    void dispose(){idleSheet.dispose();}
}
