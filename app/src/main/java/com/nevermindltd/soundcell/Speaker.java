package com.nevermindltd.soundcell;

import java.util.Date;

/**
 * Created by Nevermind on 24/08/2017.
 */

enum Position {left,right}

public class Speaker {
    private int         id=(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
    private String      name;
    private String      color="#FFFFFF";
    private Position    position;
    private int         volume=0;

    public Speaker(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Speaker(String name,String color, Position position,int volume) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.volume = volume;
    }

    public int getId(){ return id;}

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
