package com.nevermindltd.soundcell;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by jejem on 09/10/2017.
 */
@Deprecated
public class BluetoothDialog {
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Activity activity;

    protected BluetoothDialog(@NonNull Activity activity) {
        this.activity = activity;
        alertDialogBuilder= new AlertDialog.Builder(activity.getBaseContext());
        alertDialogBuilder.setTitle("Bluetooth Devices");
    }

    public void show() {

        String[] list = {"Speaker 1", "Speaker 2"};

        alertDialogBuilder.setItems(list, clicOnItemBluetoothList);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private DialogInterface.OnClickListener clicOnItemBluetoothList = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface d, int item) {
            Toast.makeText(activity, "Clic on item " + item + ".", Toast.LENGTH_SHORT).show();
        }
    };


}
