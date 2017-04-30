package com.industries118.game;

interface ActionResolver
{
    /*
        ActionResolver class has methods in it which will be implemented by AndroidActionResolver
        in order to allow LibGdx core to use Android objects and methods
    */

    //Allows a Toast to be displayed with given txt
    void toast(String txt);

    //Allows Notifications
    void randomNotification();
}
