package edu.illinois.cs.cs125.runningappugh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Rating extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
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

    public void changeActivity (View v) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                TextView notes = findViewById(R.id.notesInput);
                Run.lastGlobalRun.notes = notes.getText().toString();
            }
        });
        startActivity(new Intent(Rating.this, MainActivity.class));
    }

    public void stars (View v) {
        Button[] allStars = {findViewById(R.id.oneStar), findViewById(R.id.twoStars), findViewById(R.id.threeStars), findViewById(R.id.fourStars), findViewById(R.id.fiveStars)};
        Button button = (Button) v;
        button.setBackgroundColor(Color.YELLOW);
        for (int i = 0; i < 5; i++) {
            if (button != allStars[i]) {
                allStars[i].setBackgroundColor(Color.WHITE);
            } else {
                Run.lastGlobalRun.rating = i + 1;
            }
        }
    }
}
