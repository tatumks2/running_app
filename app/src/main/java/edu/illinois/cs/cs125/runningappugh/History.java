package edu.illinois.cs.cs125.runningappugh;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
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
                    TextView text = (TextView) findViewById(R.id.pastRunsText);
                    text.setText(Run.lastGlobalRun.getAllStrings());
                }
            });
        }
    }

    public void changeActivity (View v) {
        startActivity(new Intent(History.this, MainActivity.class));
    }

}
