package com.industries118.game;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//DBManager is a thread class that downloads JSON data from my custom online database
class DBManager extends Thread
{
    private boolean done;   //State to determine whether the thread has finished downloading the data
    private String result;  //String to store the JSON data
    private String url;     //URL to get the data from

    //Constructor to simply get JSON data of the score database
    DBManager(String tableName)
    {
        done = false;
        this.url = "http://industries118.x10host.com/android_api/results.php?table="
                +tableName;
    }

    //Constructor to upload a score and then download the JSON data
    DBManager(String name,int score,String tableName)
    {
        done = false;
        this.url = "http://industries118.x10host.com/android_api/run.php?table="
                +tableName+"&name="+name+"&score="+score;
    }

    //Method called when DBManagerObject.start() is called
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
        catch (Exception e) //In case of error, return single "No Results" result
        {
            result = "[{\"Name\":\"No Results\",\"Score\":\"0\",\"Date\":\"Network\"}]";
            done = true;
        }
    }

    //Returns status
    boolean getStatus(){return done;}

    //Return JSON results
    String getInfo(){return result;}

    //Method to read the JSON data retrieved from a website and return as String
    private String readStream(InputStream inputStream)
    {
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
        catch (IOException e)   //In case of error, return single "No Results" result
        {
            return "[{\"Name\":\"No Results\",\"Score\":\"0\",\"Date\":\"IO\"}]";
        }
    }
}
