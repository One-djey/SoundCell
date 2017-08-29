package com.nevermindltd.soundcell;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jejem on 24/08/2017.
 */

public class Cell {
    private int id =(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
    private String name;
    private ArrayList<Speaker> speakers;


    public Cell(String name) {
        this.name = name;
    }

    public void addSpeaker(Speaker speaker){
        this.speakers.add(speaker);
    }

    public void removeSpeaker(int id){
        for (int i = 0; i < this.speakers.size(); i++) {
            if(speakers.get(i).getId()==id)
                speakers.remove(i);
        }
    }
}
