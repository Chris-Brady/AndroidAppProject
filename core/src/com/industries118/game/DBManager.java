package com.industries118.game;

import com.badlogic.gdx.Gdx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager
{
    private Connection con;
    private Statement st;
    private ResultSet rs;

    private String server;
    private String user;
    private String pass;
    private String port;
    private String database;

    DBManager(String server,String user,String pass,String port,String database)
    {
        this.user = user;
        this.pass = pass;
        this.port = port;
        this.database = database;
        this.server = "jdbc:mysql://"+server+":"+port+"/";
    }

    public void connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(server, user, pass);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM arcadeTAI;");

            if (rs.next())
            {//get first result
                Gdx.app.log("DBM",rs.getString(1));
            }

        }
        catch (SQLException ex)
        {

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void getInfo()
    {

    }

    public void setInfo()
    {

    }
}
