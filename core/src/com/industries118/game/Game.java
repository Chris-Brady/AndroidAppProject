package com.industries118.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class Game extends ApplicationAdapter
{
	private static final int WIDTH = 400, HEIGHT = 800;
	private ArrayList<GameObject> gameObjects;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private InputManager input;
	float delta;

	
	@Override
	public void create ()
	{
		input = new InputManager();
		Gdx.input.setInputProcessor(input);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,WIDTH,HEIGHT);
		batch = new SpriteBatch(16);
		gameObjects = new ArrayList<GameObject>();
		createImpArray(gameObjects,WIDTH, HEIGHT, 3,3);
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		delta = Gdx.graphics.getDeltaTime();
		for(GameObject g:gameObjects)
		{
			g.draw(batch,delta);
		}
		batch.end();
	}
	
	@Override
	public void dispose ()
	{
		batch.dispose();
	}

	public void createImpArray(ArrayList a, int w, int h, int r, int c)
	{
		int wSpace = w/r;
		int hSpace = (h/c)/2;
		for(int i = 0; i<r;i++)
			for(int j = 0; j<c ;j++)
				a.add(new TapImp(i*wSpace-(40/r),(int)(j*hSpace+(w/1.2))));
	}
}
