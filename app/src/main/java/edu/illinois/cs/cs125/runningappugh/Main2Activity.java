package edu.illinois.cs.cs125.runningappugh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    Run currentRun;
    long lastTimeMilli;
    long savedTime;
    long time;

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
    }

    @Override
    protected void onStart () {
        super.onStart();
        currentRun = new Run();
        savedTime = 0;
        time = 0;
    }

    public void changeActivity (View v) {
        startActivity(new Intent(Main2Activity.this, Results.class));
    }
    public void pauseTimer (View v) {
        savedTime += time;
        currentRun.time = savedTime;
    }
    public void startTimer (View v) {
        Timer timer = new Timer();
        lastTimeMilli = System.currentTimeMillis();
        TimerTask updateTime = new TimerTask() {
            @Override
            public void run() {
                time = System.currentTimeMillis() - lastTimeMilli;
                TextView text = (TextView) findViewById(R.id.timeText);
                text.setText("Time: " + (savedTime + time));
            }
        };
        timer.scheduleAtFixedRate(updateTime, 0, 1500);
    }
}
