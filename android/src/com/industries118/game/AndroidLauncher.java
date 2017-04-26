package com.industries118.game;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication
{
	AndroidActionResolver ar;
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ar = new AndroidActionResolver(getApplicationContext());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new gameEntry(ar), config);
	}
}
