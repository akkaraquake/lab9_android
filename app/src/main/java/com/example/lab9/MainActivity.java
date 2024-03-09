package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int START_BUTTON = 1;
    private static final int PAUSE_BUTTON = 2;
    private static final int STOP_BUTTON = 3;
    private static final int COLORS_BUTTON = 4;
    private static final int BLUE_BUTTON = 5;
    private static final int RED_BUTTON = 6;
    private Chronometer chronometer;
    long timeWhenStopped = 0;
    private boolean isPaused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        // Создаем компоновку
        LinearLayout layout = findViewById(R.id.layout);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        // Создаем кнопки
        Button startButton = new Button(this);
        startButton.setText("START");
        startButton.setId(START_BUTTON);
        startButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        startButton.setOnClickListener(this);

        Button pauseButton = new Button(this);
        pauseButton.setText("PAUSE");
        pauseButton.setId(PAUSE_BUTTON);
        pauseButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        pauseButton.setOnClickListener(this);
        pauseButton.setEnabled(false);

        Button stopButton = new Button(this);
        stopButton.setText("STOP");
        stopButton.setId(STOP_BUTTON);
        stopButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        stopButton.setOnClickListener(this);

        layout.addView(startButton);
        layout.addView(pauseButton);
        layout.addView(stopButton);


        LinearLayout layout2 = findViewById(R.id.layout2);

        Button colorsButton = new Button(this);
        colorsButton.setText("COLOR >>");
        colorsButton.setId(COLORS_BUTTON);
        colorsButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        colorsButton.setOnClickListener(this);
        layout2.addView(colorsButton);


    }

    @Override
    public void onClick(View v) {
        int btn = v.getId();
        if (btn == START_BUTTON) {
            chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            chronometer.start();
            findViewById(START_BUTTON).setEnabled(false);
            findViewById(PAUSE_BUTTON).setEnabled(true);
        }
        else if (btn == PAUSE_BUTTON) {
            if (isPaused) {
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                isPaused = false;
            }
            else {
                chronometer.stop();
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                isPaused = true;
            }

        }
        else if (btn == STOP_BUTTON) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenStopped = 0;
            chronometer.stop();
            findViewById(START_BUTTON).setEnabled(true);
            findViewById(PAUSE_BUTTON).setEnabled(false);
        }
        else if (btn == COLORS_BUTTON) {
            ImageButton blueButton = new ImageButton(this);
            blueButton.setId(BLUE_BUTTON);
            blueButton.setImageResource(R.drawable.blue);
            blueButton.setOnClickListener(this);

            ImageButton redButton = new ImageButton(this);
            redButton.setId(RED_BUTTON);
            redButton.setImageResource(R.drawable.red);
            redButton.setOnClickListener(this);

            LinearLayout layout2 = findViewById(R.id.layout2);
            layout2.addView(blueButton);
            layout2.addView(redButton);

            findViewById(COLORS_BUTTON).setEnabled(false);
        }
        else if (btn == BLUE_BUTTON) {
            chronometer.setTextColor(Color.BLUE);
        }
        else if (btn == RED_BUTTON) {
            chronometer.setTextColor(Color.RED);
        }
    }
}