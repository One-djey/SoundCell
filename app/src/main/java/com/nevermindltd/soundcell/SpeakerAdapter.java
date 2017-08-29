package com.nevermindltd.soundcell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jejem on 29/08/2017.
 */

public class SpeakerAdapter extends BaseAdapter {
    List<Speaker> speakers;
    LayoutInflater mInflater ;

    SpeakerAdapter(List<Speaker> speakers, Context context){
        this.speakers =speakers;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return speakers.size();
    }

    /**
     * Récupérer un item de la liste en fonction de sa position
     * @param position - position de l'item à récupérer
     * @return l'item récupéré
     */
    public Speaker getItem(int position) {
        return speakers.get(position);
    }

    /**
     * Récupérer l'identifiant d'un item de la liste en fonction de sa position (plutôt utilisé dans le cas d'une
     * base de données, mais on va l'utiliser aussi)
     * @param position - position de l'item à récupérer
     * @return l'identifiant de l'item
     */
    public long getItemId(int position) {
        return speakers.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =  mInflater.inflate(R.layout.speaker_layout, null);
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.speaker_layout,parent, false);
        }

        SpeakerViewHolder viewHolder = (SpeakerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SpeakerViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.speakerName);
            viewHolder.alignement = (TextView) convertView.findViewById(R.id.speakerAlignement);
            convertView.setTag(viewHolder);
        }

        com.nevermindltd.soundcell.Speaker speaker = getItem(position);

        viewHolder.name.setText(speaker.getName());
        viewHolder.alignement.setText(speaker.getPosition().toString());

        return convertView;
    }

    class SpeakerViewHolder{
        public TextView name;
        public TextView alignement;
    }

}