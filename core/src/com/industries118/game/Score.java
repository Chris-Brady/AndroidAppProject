package com.industries118.game;

class Score
{
    private String Name;
    private String Score;
    private String Date;

    public Score(String name, String score,String date)
    {
        this.Name = name;
        this.Score = score;
        this.Date = date;
    }

    public String getName()
    {
        return this.Name;
    }
    public String getScore()
    {
        return this.Score;
    }
    public String getDate()
    {
        return this.Date;
    }
}
