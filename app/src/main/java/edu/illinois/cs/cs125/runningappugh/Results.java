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

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
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

        if (Run.lastGlobalRun != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textTime = (TextView) findViewById(R.id.finalTime);
                    String time = "Time: " + Run.lastGlobalRun.time/(1000) + " seconds";
                    textTime.setText(time);

                    TextView textDistance = (TextView) findViewById(R.id.finalDistance);
                    textDistance.setText("Distance: " + Run.lastGlobalRun.distance + " steps");

                    TextView textSpeed = (TextView) findViewById(R.id.avergSpeed);
                    if (Run.lastGlobalRun.time == 0) {
                        textSpeed.setText("Average Speed: 0");
                    } else {
                        textSpeed.setText("Average Speed: " + (Run.lastGlobalRun.distance / (Run.lastGlobalRun.time/1000)) + " steps/second");
                    }
                }
            });
        }
    }

    public void changeActivity (View v) {
        //Button button = (Button) v;
        startActivity(new Intent(Results.this, MainActivity.class));
    }

    public void changeActivityRating (View v) {
        startActivity(new Intent(Results.this, Rating.class));
    }

    public void buttonOnClick(View v) {
        Button button = (Button) v;
        ( (Button) v) .setText("clicker");
    }
}
