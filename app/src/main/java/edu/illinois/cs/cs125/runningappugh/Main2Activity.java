package edu.illinois.cs.cs125.runningappugh;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {
    Run currentRun;
    long lastTimeMilli;
    long savedTime;
    long time;
    Timer timer;
    long distance;
    TextView distanceText;
    SensorManager sensorManager;
    float steps;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onStart () {
        super.onStart();
        if (Run.lastGlobalRun != null) {
            currentRun = new Run(Run.lastGlobalRun);
        } else {
            currentRun = new Run(null);
        }
        Run.lastGlobalRun = currentRun;
        savedTime = 0;
        time = 0;
    }

    public void changeActivity (View v) {
        currentRun.time = time + savedTime;
        currentRun.distance = distance;
        startActivity(new Intent(Main2Activity.this, Results.class));
    }
    public void pauseTimer (View v) {
        savedTime += time;
        currentRun.time = savedTime;
        timer.cancel();
        timer.purge();
        time = 0;

        onPause();

    }
    public void startTimer (View v) {
        timer = new Timer();
        lastTimeMilli = System.currentTimeMillis();
        Log.d("ks2", "" + lastTimeMilli);
        TimerTask updateTime = new TimerTask() {
            @Override
            public void run() {
                time = System.currentTimeMillis() - lastTimeMilli;
                Log.d("ks2", "" + (time / 1000));
                Log.d("ks2", "" + ((time / 1000) + (savedTime / 1000)));

                //setContentView(R.layout.activity_main2);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.timeText);
                        text.setText("Time: " + ((time / 1000) + (savedTime / 1000)));
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(updateTime, 0, 1000);
        onResume();
    }

    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onPause() {
        super.onPause();
        running = false;
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            steps = event.values[0];
            distanceText = (TextView) findViewById(R.id.distanceText);
            distanceText.setText("Distance: " + steps + " or about " + (steps/2112) + " miles");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
