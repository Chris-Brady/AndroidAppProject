package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

//Screen fpr Tap An Imp Game
class TapImpGame implements Screen
{
	private GameEntry game;						//Variable to store GameEntry for access
	private ArrayList<GameObject> gameObjects;	//ArrayList of GameObjects
	private Vector3 touchInput;					//Vector storing x,y,z of touch location
	private Texture bg;							//Background texture
	private CustomFont mFont,sFont;				//Fonts for score and time remaining
	private Music music;						//LibGdx Music Object

	private boolean running;					//True if game is not paused
	private int timeLeft;						//Time left
	private long time;							//Current time
	private long startTime;						//Time Game was started at
	private long timeOffset;					//Time elapsed while game has been paused
	private long timePaused;					//Time game was paused at

	//Constructor
	TapImpGame(final GameEntry game)
	{
		this.game = game;
		GameEntry.TAP_AN_IMP_SCORE = 0;
		game.camera = new OrthographicCamera();
		game.camera.setToOrtho(false, GameEntry.WIDTH, GameEntry.HEIGHT);
		gameObjects = new ArrayList<GameObject>();
		touchInput = new Vector3(0,0,0);
		mFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
		sFont = new CustomFont("LCD2 Bold.ttf",40, Color.YELLOW);
		bg = new Texture("floor.png");
		music = Gdx.audio.newMusic(Gdx.files.internal("sfx/headshredder.ogg"));
		music.play();
		music.setLooping(true);
		createImpArray(gameObjects, GameEntry.WIDTH, GameEntry.HEIGHT, 3,3);
		time = 0;
		timeLeft = 30000;
		timeOffset = 0;
		running = false;
	}

	//Called when Screen comes into view
	@Override
	public void show ()
	{
		startTime = System.currentTimeMillis();
		running = true;
	}

	//Called 30 or 60 times a second
	@Override
	public void render (float delta)
	{
		if(running&&timeLeft >=0)
		{
			time = System.currentTimeMillis()-timeOffset;
			delta = Gdx.graphics.getDeltaTime();
			timeLeft = 30 - (int) (time - startTime) / 1000;
			game.setCameraBits();
			game.batch.begin();
			game.batch.draw(bg, 0, 0, GameEntry.WIDTH, GameEntry.HEIGHT);
			mFont.draw(game.batch,"Score: "+ GameEntry.TAP_AN_IMP_SCORE,(GameEntry.WIDTH/2)-(mFont.getWidth()/2), GameEntry.HEIGHT-60);
			sFont.draw(game.batch,"Time: "+(timeLeft),(GameEntry.WIDTH/2)-(sFont.getWidth()/2), GameEntry.HEIGHT-24);


			for (GameObject g : gameObjects)
			{
				g.update(time);
				if (g instanceof TapImp)
					if (Gdx.input.isTouched())
					{
						touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
						game.camera.unproject(touchInput);
						((TapImp) g).touchUpdate(touchInput, time);
						((TapImp) g).setSpeed(((TapImp) g).getSpeed()-1f);
					}
				g.draw(game.batch, delta);
			}
			game.batch.end();
		}
		else if(timeLeft<=0)
		{
			game.setScreen(new LeaderBoard(game, GameEntry.TAP_AN_IMP_SCORE, 0));
			dispose();
		}
		if(timeLeft<=5&&music.isPlaying()&&music.getVolume()>0)
			music.setVolume(music.getVolume()-delta*0.1f);
	}

	//Called when Screen loses focus
	@Override
	public void pause()
	{
		if(running)
		{
			timePaused = System.currentTimeMillis();
			running = false;
			music.pause();
		}
	}

	//Called after Screen regains focus
	@Override
	public void resume()
	{
		timeOffset += System.currentTimeMillis()-timePaused;
		running = true;
		music.play();
	}

	//Called before screen loses focus
	@Override
	public void hide()
	{
		music.stop();
	}

	//Called when switching Screens
	@Override
	public void dispose ()
	{
		bg.dispose();
		mFont.dispose();
		sFont.dispose();
		music.dispose();
		for(GameObject g:gameObjects)
			g.dispose();
	}

	@Override
	public void resize(int width, int height) {/*Unused implement method*/}

	//Creates a grid of TapImp Objects
	private void createImpArray(ArrayList<GameObject> a, int w, int h, int r, int c)
	{
		int wSpace = w/r;
		int hSpace = (h/c)/2;
		for(int i = 0; i<r;i++)
			for(int j = 0; j<c ;j++)
				a.add(new TapImp(i*wSpace-(40/r),(int)(j*hSpace+(w/1.2))));
	}
}