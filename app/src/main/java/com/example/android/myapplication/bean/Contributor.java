package com.example.android.myapplication.bean;

/**
 * Created by Android on 2016/9/1.
 */
public class Contributor {

    public String login;
    public int contributions;

    @Override
    public String toString() {
        return login + ", " + contributions;
    }
}