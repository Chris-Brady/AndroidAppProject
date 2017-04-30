package com.industries118.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

//TapImp Object for tappable imps
class TapImp extends GameObject
{
    //GameLogic
    private long popTime;                       //Time imp popped at
    private long deathTime;                     //Time imp died at
    private boolean popped = false;             //Imp is visible if popped
    private float speed;                        //Time before imp can re-pop
    private Random r;                           //Random number for imp pop time

    //Sfx
    private Sound impPop;
    private Sound score;

    TapImp(float x, float y)
    {
        super(x,y);                             //Invoke GameObject constructor to set width and height
        setWidth(160);                          //Set height and width
        setHeight(130);
        speed = 1000;                           //Time for imp to have re-pop chance
        setAnim("impspawn.png",16,1,0.07f);     //Set up animation frames and time
        r = new Random();                       //Random number generator to determine when imp will pop
        deathTime = System.currentTimeMillis(); //Set all imps dead at the beginning
        impPop = Gdx.audio.newSound(Gdx.files.internal("sfx/imppop.ogg"));  //Load audio
        score = Gdx.audio.newSound(Gdx.files.internal("sfx/score.ogg"));
    }

    void update(long time)  //Update method which determines whether the imp will die, pop, or un-pop in the current frame
    {
        if(!popped)
        {
            int random = r.nextInt(1000);   //Get random number
            if(((time-deathTime)>speed)&&(random > 666&& random <685))
            {
                impPop.play(0.2f);  //Play pop sound
                popTime = time;     //popTime = now
                stateTime = 0f;     //Animation state reset
                popped = true;      //Imp is now popped
            }
        }
        else
        {
            if((time-popTime)>1000) //Imp has one second to be killed before hiding
            {
                deathTime = time;   //deathTime = now
                popped = false;     //Imp is now not popped
            }
        }
    }

    void touchUpdate(Vector3 input, long time)
    {
        if(popped)                                                  //If imp is popped up
            if(input.x > getX()&&input.x < getX()+getWidth())       //And the touchX pos is in bounds
                if(input.y>getY()&&input.y < getY()+getHeight())    //And the touchY pos is in bounds
                    kill(time);                                     //Kill the imp
    }

    @Override
    void draw(SpriteBatch batch, float delta)    //Draws the imps animation frame at its position
    {
        stateTime += delta;
        TextureRegion currentFrame = idleAnim.getKeyFrame(stateTime, true);
        if(popped)
            batch.draw(currentFrame,getX(),getY(),getWidth(),getHeight());
    }

    void dispose()  //Disposes of all libgdx disposables to prevent memory leaks
    {
        idleSheet.dispose();
        impPop.dispose();
        score.dispose();
    }

    private void kill(long time)
    {
        deathTime = time;               //Set imp death time
        score.play(1.0f);               //Play score blip
        GameEntry.TAP_AN_IMP_SCORE++;   //Increment game score
        popped = false;                 //Set popped false
    }
    float getSpeed() {return speed;}                    //Set time before imp can re-pop

    void setSpeed(float speed) {this.speed = speed;}    //Set time before imp can re-pop
}
