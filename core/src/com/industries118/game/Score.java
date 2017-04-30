package com.industries118.game;

//Class to store JSON data as a single score Object
class Score
{
    private String Name;       //Name associated with a score
    private String Score;      //Score
    private String Date;       //Date Associated with a score

    //Constructor
    public Score(String name, String score,String date)
    {
        this.Name = name;
        this.Score = score;
        this.Date = date;
    }

    //Get Name
    String getName(){return this.Name;}

    //Get Score
    String getScore(){return this.Score;}

    //Get Date
    String getDate(){return this.Date;}
}
