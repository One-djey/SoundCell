package com.nevermindltd.soundcell;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jejem on 29/08/2017.
 */

public class BluetoothItemAdapter extends BaseAdapter {
    ArrayList<BluetoothDevice> devices;
    LayoutInflater mInflater ;

    BluetoothItemAdapter(ArrayList<BluetoothDevice> devices, Context context){
        this.devices = devices;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    /**
     * Récupérer un item de la liste en fonction de sa position
     * @param position - position de l'item à récupérer
     * @return l'item récupéré
     */
    public BluetoothDevice getItem(int position) {
        return devices.get(position);
    }

    /**
     * Récupérer l'identifiant d'un item de la liste en fonction de sa position (plutôt utilisé dans le cas d'une
     * base de données, mais on va l'utiliser aussi)
     * @param position - position de l'item à récupérer
     * @return l'identifiant de l'item
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =  mInflater.inflate(R.layout.bluetooth_item_layout, null);
        }

        BluetoothItemViewHolder viewHolder = (BluetoothItemViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BluetoothItemViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.bluetoothItemName);
            viewHolder.address = (TextView) convertView.findViewById(R.id.bluetoothItemAddress);
            viewHolder.known = (ImageView) convertView.findViewById(R.id.bluetoothItemKnown);
            convertView.setTag(viewHolder);
        }

        BluetoothDevice device = getItem(position);

        viewHolder.name.setText(device.getName());
        viewHolder.address.setText(device.getAddress());

        //check if current device is known
        ArrayList<BluetoothDevice> knownDevices = new ArrayList<BluetoothDevice>();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        knownDevices.addAll(bluetoothAdapter.getBondedDevices());
        Boolean known = false;
        for(BluetoothDevice aDevice : knownDevices)
            if(aDevice.getAddress().equals(device.getAddress()))
                known = true;
        if(known == true){
            viewHolder.known.setVisibility(View.VISIBLE);
        }else
            viewHolder.known.setVisibility(View.GONE);

        return convertView;
    }

    class BluetoothItemViewHolder {
        public TextView name;
        public TextView address;
        public ImageView known;
    }

}