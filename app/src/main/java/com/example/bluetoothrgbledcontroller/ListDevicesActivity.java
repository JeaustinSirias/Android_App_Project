package com.example.bluetoothrgbledcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

public class ListDevicesActivity extends AppCompatActivity {
    BluetoothAdapter adapter_bluetooth;
    ListView showDevices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_devices);

        adapter_bluetooth = BluetoothAdapter.getDefaultAdapter();
        showDevices = (ListView) findViewById(R.id.ListDevices);
        ListPairedDevices();
    }

    private void ListPairedDevices() {
        Set<BluetoothDevice> bt = adapter_bluetooth.getBondedDevices();
        String[] strings = new String[bt.size()];
        int index = 0;
        if(bt.size()>0){
            for(BluetoothDevice device:bt){
                strings[index] = device.getName();
                index = index + 1;
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
            showDevices.setAdapter(arrayAdapter);

        }


    }
}