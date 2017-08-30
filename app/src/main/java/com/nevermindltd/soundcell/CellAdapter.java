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

public class CellAdapter extends BaseAdapter {
    List<Cell> cells;
    LayoutInflater mInflater ;

    CellAdapter(List<Cell> cells, Context context){
        this.cells =cells;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    /**
     * Récupérer un item de la liste en fonction de sa position
     * @param position - position de l'item à récupérer
     * @return l'item récupéré
     */
    public Cell getItem(int position) {
        return cells.get(position);
    }

    /**
     * Récupérer l'identifiant d'un item de la liste en fonction de sa position (plutôt utilisé dans le cas d'une
     * base de données, mais on va l'utiliser aussi)
     * @param position - position de l'item à récupérer
     * @return l'identifiant de l'item
     */
    public long getItemId(int position) {
        return cells.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =  mInflater.inflate(R.layout.cell_layout, null);
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_layout,parent, false);
        }

        CellViewHolder viewHolder = (CellViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CellViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.cellName);
            convertView.setTag(viewHolder);
        }

        Cell cell = getItem(position);

        viewHolder.name.setText(cell.getName());

        return convertView;
    }

    class CellViewHolder{
        public TextView name;
    }

}