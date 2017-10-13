package com.nevermindltd.soundcell;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.souncell.MESSAGE";
    private ArrayList<BluetoothDevice> devices;
    AlertDialog bluetoothDialog;
    private ListView rightSpeakeersView;
    private ListView leftSpeakeersView;
    private ListView cellsView;
    private ImageView addSpeaker;
    private LinearLayout messageView;
    private Button add;
    AlertDialog.Builder bluetoothListBuilder;
    BluetoothAdapter bluetoothAdapter;
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Boolean known = false;
                for(BluetoothDevice aDevice : devices)
                    if(aDevice.getAddress().equals(device.getAddress()))
                        known = true;
                if(known == false) {
                    devices.add(device);
                    Toast.makeText(MainActivity.this, "New device found : " + device.getName(), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Device already founded : " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightSpeakeersView = (ListView) findViewById(R.id.LayoutRightSpeakers);
        leftSpeakeersView = (ListView) findViewById(R.id.LayoutLeftSpeakers);
        addSpeaker = (ImageView) findViewById(R.id.addSpeaker);
        add = (Button) findViewById(R.id.add);
        messageView = (LinearLayout) findViewById(R.id.messageView);
        //cellsView = (ListView) findViewById(R.id.LayoutCells);

        List<Speaker> speakers = getSpeakers();
        List<Cell> cells = getCells();

        List<Speaker> speakersRIGHT = new ArrayList<Speaker>();
        List<Speaker> speakersLEFT = new ArrayList<Speaker>();
        for (Speaker speaker : speakers) {
            if (speaker.getPosition() == Position.left)
                speakersLEFT.add(speaker);
            if (speaker.getPosition() == Position.right)
                speakersRIGHT.add(speaker);
        }

        leftSpeakeersView.setAdapter(new SpeakerAdapter(speakersLEFT, getApplicationContext()));
        rightSpeakeersView.setAdapter(new SpeakerAdapter(speakersRIGHT, getApplicationContext()));
        addSpeaker.setOnClickListener(clicAddSpeackerListerner);
        add.setOnClickListener(clicOnCell);
        //cellsView.setAdapter( new CellAdapter(cells,getApplicationContext()));



        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            sendMessage(messageView,"Sorry !\nYou do not have bluetooth on your device.");
        }else {
            if (!bluetoothAdapter.isEnabled())
                bluetoothAdapter.enable();
            bluetoothListBuilder = new AlertDialog.Builder(this);
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(bluetoothReceiver, filter);
            devices = new ArrayList<BluetoothDevice>();
            devices.addAll(bluetoothAdapter.getBondedDevices());
            bluetoothAdapter.startDiscovery();

            setTheme(R.style.AppTheme); // stop splash screen
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(bluetoothReceiver);
        if(bluetoothAdapter.isEnabled())
            bluetoothAdapter.disable();
    }



    private OnClickListener clicAddSpeackerListerner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //bluetoothAdapter.cancelDiscovery();
            do  {
                bluetoothAdapter.enable();
            }while(!bluetoothAdapter.isEnabled());
           if( !bluetoothAdapter.isDiscovering())
               bluetoothAdapter.startDiscovery();

            ArrayList<String> list = new ArrayList<String>();

            for (BluetoothDevice bluetoothDevice : devices) {
                list.add(bluetoothDevice.getName());
            }

            if(list.size() != 0) {
                bluetoothListBuilder.setTitle("Bluetooth Devices");
                //bluetoothListBuilder.setItems(list.toArray(new String[0]), clicOnItemBluetoothList);
                BluetoothItemAdapter bluetoothItemAdapter = new BluetoothItemAdapter(devices,getApplicationContext());
                bluetoothListBuilder.setAdapter(bluetoothItemAdapter,clicOnItemBluetoothList);
                //bluetoothListBuilder.setView()
                bluetoothDialog = bluetoothListBuilder.create();
                bluetoothDialog.show();
            }else{
                Toast.makeText(MainActivity.this, "No devices found", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private DialogInterface.OnClickListener clicOnItemBluetoothList = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface d,int item) {
            Toast.makeText(MainActivity.this, "Clic on item "+item+".", Toast.LENGTH_SHORT).show();
        }
    };

    private OnClickListener clicOnCell = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Clic on cell", Toast.LENGTH_SHORT).show();
        }
    };

    private List<Speaker> getSpeakers() {
        List<Speaker> speakers = new ArrayList<Speaker>();
        speakers.add(new Speaker("premier", Position.left));
        speakers.add(new Speaker("second", Position.right));
        speakers.add(new Speaker("troisième", Position.left));
        speakers.add(new Speaker("quatrième", Position.right));
        speakers.add(new Speaker("cinquième", Position.left));
        speakers.add(new Speaker("sixième", Position.right));
        return speakers;
    }

    private List<Cell> getCells() {
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(new Cell("première"));
        cells.add(new Cell("seconde"));
        cells.add(new Cell("troisième"));
        return cells;
    }

    public void sendMessage(View view,String message) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
