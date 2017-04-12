package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

class TapImpGame implements Screen
{
	private gameEntry game;
	private ArrayList<GameObject> gameObjects;
	private Vector3 touchInput;
	private Texture bg;
	private CustomFont mFont,sFont;


	private boolean running;
	private int timeLeft;
	private long time;
	private long startTime;
	private long timeOffset;
	private long timePaused;

	TapImpGame(final gameEntry game)
	{
		this.game = game;
		gameEntry.TAP_AN_IMP_SCORE = 0;
		game.camera = new OrthographicCamera();
		game.camera.setToOrtho(false,gameEntry.WIDTH,gameEntry.HEIGHT);
		gameObjects = new ArrayList<GameObject>();
		touchInput = new Vector3(0,0,0);
		mFont = new CustomFont("BLOODY.TTF",24, Color.YELLOW);
		sFont = new CustomFont("LCD2 Bold.ttf",40, Color.YELLOW);
		bg = new Texture("floor.png");
		createImpArray(gameObjects,gameEntry.WIDTH, gameEntry.HEIGHT, 3,3);
		timeLeft = 30000;
		timeOffset = 0;
		startTime = System.currentTimeMillis();
		running = true;
	}

	@Override
	public void show ()
	{

	}

	@Override
	public void render (float delta)
	{
		if(running&&timeLeft >=0)
		{
			time = System.currentTimeMillis()-timeOffset;
			delta = Gdx.graphics.getDeltaTime();
			timeLeft = 30 - (int) (time - startTime) / 1000;
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			game.camera.update();
			game.batch.setProjectionMatrix(game.camera.combined);
			game.batch.begin();
			game.batch.draw(bg, 0, 0, gameEntry.WIDTH, gameEntry.HEIGHT);
			mFont.draw(game.batch,"Score: "+gameEntry.TAP_AN_IMP_SCORE,(gameEntry.WIDTH/2)-(mFont.getWidth()/2),gameEntry.HEIGHT-60);
			sFont.draw(game.batch,"Time: "+(timeLeft),(gameEntry.WIDTH/2)-(sFont.getWidth()/2),gameEntry.HEIGHT-24);


			for (GameObject g : gameObjects)
			{
				g.update(time);
				if (g instanceof TapImp)
					if (Gdx.input.isTouched())
					{
						touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
						game.camera.unproject(touchInput);
						((TapImp) g).touchUpdate(touchInput);
					}
				g.draw(game.batch, delta);
			}
			game.batch.end();
		}
		else
		{
			game.setScreen(new LeaderBoard(game, gameEntry.TAP_AN_IMP_SCORE));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void pause()
	{
		if(running)
			timePaused = System.currentTimeMillis();
	}

	@Override
	public void resume()
	{
		timeOffset += System.currentTimeMillis()-timePaused;
	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose ()
	{
		bg.dispose();
		mFont.dispose();
		sFont.dispose();
		for(GameObject g:gameObjects)
			g.dispose();
	}

	private void createImpArray(ArrayList a, int w, int h, int r, int c)
	{
		int wSpace = w/r;
		int hSpace = (h/c)/2;
		for(int i = 0; i<r;i++)
			for(int j = 0; j<c ;j++)
				a.add(new TapImp(i*wSpace-(40/r),(int)(j*hSpace+(w/1.2))));
	}
}
