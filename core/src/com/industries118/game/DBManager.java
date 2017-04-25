package com.industries118.game;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class DBManager extends Thread
{
    private boolean done;
    private String result;
    private String url;

    DBManager(String tableName)
    {
        done = false;
        this.url = "http://industries118.x10host.com/android_api/results.php?table="
                +tableName;
    }

    DBManager(String name,int score,String tableName)
    {
        done = false;
        this.url = "http://industries118.x10host.com/android_api/run.php?table="
                +tableName+"&name="+name+"&score="+score;
    }

    @Override
    public void run()
    {
        try
        {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            result = readStream(in);
            connection.disconnect();
            done = true;
        }
        catch (Exception e)
        {
            result = "[{\"Name\":\"No Results\",\"Score\":\"0\",\"Date\":\"null\"}]";
        }
    }

    boolean getStatus(){return done;}

    String getInfo(){return result;}

    private String readStream(InputStream inputStream) {
        try
        {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int byteRead = inputStream.read();
            while (byteRead != -1)
            {
                bo.write(byteRead);
                byteRead = inputStream.read();
            }
            return bo.toString(); // return string that was read.
        }
        catch (IOException e)
        {
            return "[{\"Name\":\"No Results\",\"Score\":\"0\",\"Date\":\"null\"}]";
        }
    }
}
