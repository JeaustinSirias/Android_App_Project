package com.example.bluetoothrgbledcontroller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private TextView bar_progress;
    private SeekBar intensity_bar;
    Button power_button;
    boolean power_state = false;

    // bluetooth variables/elements
    BluetoothSocket hc_socket = null;
    Button bluetooth_button;
    BluetoothAdapter adapter_bluetooth;
    private boolean connected_to;
    Intent bluetooth_enablingIntent;
    int requestCode_bluetooth;
    //static final UUID mUUID = UUID.fromString("00001133-0000-1000-8000-00805f9b34fb");
    private final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    // color light elements
    Button white_button;
    Button blue_button;
    Button purple_button;
    Button yellow_button;
    Button green_button;
    Button red_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // turn on/off button
        power_button = findViewById(R.id.led_button);
        // color light elements
        white_button = findViewById(R.id.white_button);
        blue_button = findViewById(R.id.blue_button);
        purple_button = findViewById(R.id.purple_button);
        yellow_button = findViewById(R.id.yellow_button);
        green_button = findViewById(R.id.green_button);
        red_button = findViewById(R.id.red_button);
        // progress bar elements
        bar_progress = findViewById(R.id.bar_progress);
        intensity_bar = findViewById(R.id.intensity_bar);
        // connectivity to bluetooth
        bluetooth_button = findViewById(R.id.connect_button);
        adapter_bluetooth = BluetoothAdapter.getDefaultAdapter();
        connected_to  = false;
        bluetooth_enablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCode_bluetooth = 1;

        attachElements();
        BluetoothConnection();
    }

    // message to print result of connection trying
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == requestCode_bluetooth){
            if(resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(), "Bluetooth has been enabled", Toast.LENGTH_LONG).show();
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

                // if bluetooth is not enabled*********
                if(!connected_to){
                    if(adapter_bluetooth == null){
                        Toast.makeText(getApplicationContext(), "Bluetooth not supported on this device", Toast.LENGTH_LONG).show();
                    }else{
                        if(!adapter_bluetooth.isEnabled()){
                            startActivityForResult(bluetooth_enablingIntent, requestCode_bluetooth);
                        }
                    }
                }//************************************

                // if bluetooth is connected to a device
                if(adapter_bluetooth.isEnabled() && !connected_to){
                    //BluetoothDevice hc_05 = adapter_bluetooth.getRemoteDevice("54:8D:5A:20:7B:EA");//"98:D3:61:F5:CB:58"); PC
                    BluetoothDevice hc_05 = adapter_bluetooth.getRemoteDevice("98:D3:61:F5:CB:58");//HC-05
                    int tryToConnect = 0;
                    do {
                        try {
                            hc_socket = hc_05.createRfcommSocketToServiceRecord(mUUID);
                            hc_socket.connect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(tryToConnect > 3){
                            tryToConnect = 0;
                            Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_LONG).show();
                            break;
                        }else{
                            tryToConnect++;
                        }

                    }while(!hc_socket.isConnected());

                    if(hc_socket.isConnected()){
                        connected_to = true;
                        Toast.makeText(getApplicationContext(), "Connection Successful", Toast.LENGTH_LONG).show();
                        bluetooth_button.setText(R.string.on_connect);
                    }
                }else if(!connected_to){
                    Toast.makeText(getApplicationContext(), "Bluetooth not enabled", Toast.LENGTH_LONG).show();
                }
            }

        });

    }// end of BluetoothConnection

    private void sendColor(String color, BluetoothSocket thisSocket){
        if (thisSocket!=null)  {
            try {
                thisSocket.getOutputStream().write(color.toString().getBytes());
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void attachElements(){

        //  intensity LED progress bar control
        intensity_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //int intensity = 0;
            String intense = "0";
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intense = String.format("%d", progress);
                bar_progress.setText(intense);
                sendColor(intense, hc_socket);
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
                if(!power_state){
                    sendColor("on", hc_socket);
                    power_state = true;
                }else if(power_state){
                    sendColor("off", hc_socket);
                    power_state = false;
                }
            }
        });

        // color buttons on click listeners
        white_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("white", hc_socket);
            }
        });
        blue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("blue", hc_socket);

            }
        });
        purple_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("purple", hc_socket);

            }
        });
        yellow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("yellow", hc_socket);

            }
        });
        green_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("green", hc_socket);

            }
        });
        red_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendColor("red", hc_socket);
            }
        });


    }

}