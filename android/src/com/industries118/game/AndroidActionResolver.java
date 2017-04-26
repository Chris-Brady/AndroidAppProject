package com.industries118.game;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class AndroidActionResolver implements ActionResolver
{
    Context c;
    Handler h;

    public AndroidActionResolver(Context c)
    {
        h = new Handler();
        this.c = c;
    }

    @Override
    public void toast(final String txt)
    {
        h.post(new Runnable(){
            @Override
            public void run(){
                Toast.makeText(c,txt,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
