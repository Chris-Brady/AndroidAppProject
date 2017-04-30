package com.industries118.game;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

class AndroidActionResolver implements ActionResolver
{
    private Context c;
    private Handler h;

    //Constructor
    AndroidActionResolver(Context c)
    {
        h = new Handler();
        this.c = c;
    }

    //Make toast with given txt as message
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

    //Generate a random notification for fun
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void randomNotification()
    {
        Random r = new Random();
        String[] random = { "Don't forget to play again!","Satan Likes Cookies!","Impy Says: \'Get Good Scrub!\'!",
                            "Thanks for playing!", "Spot says: \'Bork!\'","Sell your soul for just $6.66!",
                            "For ages 6 - 66!", "May contain small parts"};
        Notification notification = new Notification.Builder(c)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Arcade!")
                .setContentText(random[r.nextInt(random.length)])
                .setSmallIcon(R.drawable.smalllogo)
                .setLargeIcon(BitmapFactory.decodeResource( c.getResources(), R.drawable.applogo))
                .setAutoCancel(false)
                .setVisibility(1)
                .build();
        NotificationManager notificationManager=(NotificationManager)c.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,notification);
    }
}
