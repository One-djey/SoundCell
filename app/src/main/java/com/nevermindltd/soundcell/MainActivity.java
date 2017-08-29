package com.nevermindltd.soundcell;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView rightSpeakeersView;
    private ListView leftSpeakeersView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightSpeakeersView = (ListView) findViewById(R.id.LayoutRightSpeakers);
        leftSpeakeersView = (ListView) findViewById(R.id.LayoutLeftSpeakers);

        List<Speaker> speakers = getSpeakers();

        leftSpeakeersView.setAdapter( new SpeakerAdapter(speakers,getApplicationContext()));

    }
    private List<Speaker> getSpeakers(){
        List<Speaker> speakers = new ArrayList<Speaker>();
        speakers.add(new Speaker("premier",Position.left));
        speakers.add(new Speaker("second",Position.right));
        speakers.add(new Speaker("troisième",Position.left));
        speakers.add(new Speaker("quatrième",Position.right));
        return speakers;
    }
}
