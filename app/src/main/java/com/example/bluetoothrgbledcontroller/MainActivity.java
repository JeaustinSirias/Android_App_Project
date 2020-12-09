package com.example.bluetoothrgbledcontroller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView bar_progress;
    private SeekBar intensity_bar;
    Button power_button;

    // bluetooth variables/elements
    Button bluetooth_button;
    public BluetoothAdapter adapter_bluetooth;
    private boolean connected_to;
    Intent bluetooth_enablingIntent;
    int requestCode_bluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // conectivity to bluetooth
        bluetooth_button = findViewById(R.id.connect_button);
        adapter_bluetooth = BluetoothAdapter.getDefaultAdapter();
        connected_to  = false;
        bluetooth_enablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCode_bluetooth = 1;

        BluetoothConnection();

        // turn on/off button
        power_button = findViewById(R.id.led_button);

        // color light elements
        Button white_button = findViewById(R.id.white_button);
        Button blue_button = findViewById(R.id.blue_button);
        Button purple_button = findViewById(R.id.purple_button);
        Button yellow_button = findViewById(R.id.yellow_button);
        Button green_button = findViewById(R.id.green_button);
        Button red_button = findViewById(R.id.red_button);
        // progress bar elements
        bar_progress = findViewById(R.id.bar_progress);
        intensity_bar = findViewById(R.id.intensity_bar);

        //  intensity LED progress bar control
        intensity_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int intensity = 0;
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intensity = progress;
                bar_progress.setText(String.format("%d", intensity));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                bar_progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                bar_progress.setVisibility(View.INVISIBLE);
            }
        });

        // Power led button listener
        power_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // color buttons on click listeners
        white_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        blue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        purple_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        yellow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        green_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        red_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == requestCode_bluetooth){
            if(resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(), "Bluetooth has been enabled", Toast.LENGTH_LONG).show();
                connected_to = true;
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Bluetooth cannot be enabled", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void BluetoothConnection() {
        // Bluetooth click on listener
        bluetooth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if bluetooth is not connected
                if(!connected_to){
                    if(adapter_bluetooth == null){
                        Toast.makeText(getApplicationContext(), "Bluetooth not supported on this device", Toast.LENGTH_LONG).show();
                    }else{
                        if(!adapter_bluetooth.isEnabled()){
                            startActivityForResult(bluetooth_enablingIntent, requestCode_bluetooth);
                        }
                    }
                }

                // if bluetooth is connected to a device
                if(connected_to){
                    if(adapter_bluetooth.isEnabled()){
                        adapter_bluetooth.disable();
                        connected_to = false;
                    }
                }

            }
        });


    }


}