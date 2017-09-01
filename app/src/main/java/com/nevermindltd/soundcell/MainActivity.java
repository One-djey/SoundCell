package com.nevermindltd.soundcell;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView rightSpeakeersView;
    private ListView leftSpeakeersView;
    private ListView cellsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightSpeakeersView = (ListView) findViewById(R.id.LayoutRightSpeakers);
        leftSpeakeersView = (ListView) findViewById(R.id.LayoutLeftSpeakers);
        cellsView = (ListView) findViewById(R.id.LayoutCells);

        List<Speaker> speakers = getSpeakers();
        List<Cell> cells = getCells();

        List<Speaker> speakersRIGHT=new ArrayList<Speaker>();
        List<Speaker> speakersLEFT=new ArrayList<Speaker>();
        for (Speaker speaker:speakers) {
            if (speaker.getPosition() == Position.left)
                speakersLEFT.add(speaker);
            if (speaker.getPosition() == Position.right)
                speakersRIGHT.add(speaker);
        }

        leftSpeakeersView.setAdapter( new SpeakerAdapter(speakersLEFT,getApplicationContext()));
        rightSpeakeersView.setAdapter( new SpeakerAdapter(speakersRIGHT,getApplicationContext()));
        cellsView.setAdapter( new CellAdapter(cells,getApplicationContext()));

    }
    private List<Speaker> getSpeakers(){
        List<Speaker> speakers = new ArrayList<Speaker>();
        speakers.add(new Speaker("premier",Position.left));
        speakers.add(new Speaker("second",Position.right));
        speakers.add(new Speaker("troisième",Position.left));
        speakers.add(new Speaker("quatrième",Position.right));
        return speakers;
    }
    private List<Cell> getCells(){
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(new Cell("première"));
        cells.add(new Cell("seconde"));
        cells.add(new Cell("troisième"));
        return cells;
    }
}
