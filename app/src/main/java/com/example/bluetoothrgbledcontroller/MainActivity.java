package com.example.bluetoothrgbledcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView bar_progress;
    private SeekBar intensity_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // conectivity button
        Button bluetooth_button = findViewById(R.id.connect_button);

        // turn on/off button
        Button power_button = findViewById(R.id.led_button);

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
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intensity = progress;
                bar_progress.setText(""+intensity);
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

        // Bluetooth click on listener
        bluetooth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



}