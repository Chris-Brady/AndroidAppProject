package com.industries118.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

class TapImpGame implements Screen
{
	private static final int WIDTH = 400, HEIGHT = 800;
	private gameEntry game;
	private ArrayList<GameObject> gameObjects;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private long time;
	private Vector3 touchInput;
	private Texture bg;
	private BitmapFont font;
	private boolean running;
	private long startTime;
	private int timeLeft;

	TapImpGame(final gameEntry game)
	{
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false,WIDTH,HEIGHT);
		batch = new SpriteBatch(16);
		gameObjects = new ArrayList<GameObject>();
		bg = new Texture("floor.png");
		touchInput = new Vector3(0,0,0);
		createImpArray(gameObjects,WIDTH, HEIGHT, 3,3);
		font = new BitmapFont();
		startTime = System.currentTimeMillis();
		timeLeft = 30000;
		gameEntry.TAP_AN_IMP_SCORE = 0;
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
			time = System.currentTimeMillis();
			delta = Gdx.graphics.getDeltaTime();
			timeLeft = 30 - (int) (time - startTime) / 1000;
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(bg, 0, 0, 400, 800);
			font.draw(batch, "Score: " + gameEntry.TAP_AN_IMP_SCORE, 20, HEIGHT - 20);
			font.draw(batch, "Time: " + (timeLeft), WIDTH / 2, HEIGHT - 20);
			for (GameObject g : gameObjects)
			{
				g.update(time);
				if (g instanceof TapImp)
					if (Gdx.input.isTouched())
					{
						touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
						camera.unproject(touchInput);
						((TapImp) g).touchUpdate(touchInput);
					}
				g.draw(batch, delta);
			}
			batch.end();
		}
		else
		{
			game.setScreen(new MainMenu(game));
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

	}

	@Override
	public void resume()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		bg.dispose();
		font.dispose();

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
