package net.nichnologist.hotspot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Login extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        try{
            Boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
            if(isFirstRun){
                Tools.toastShort("First run.", getApplicationContext());
                prefs.edit().putBoolean("isFirstRun", false).apply();
            }
            if(!isFirstRun){
                Tools.toastShort("Not first run.", getApplicationContext());
            }
        }
        catch(Exception e){
            Tools.toastShort("Error on reading shared preferences.", getApplicationContext());
        }

        final Intent menuIntent = new Intent(this, MapsActivity.class);


        FloatingActionButton mapButton = (FloatingActionButton) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Passing through to map", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(menuIntent);
            }
        });

        FloatingActionButton resetButton = (FloatingActionButton) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().clear().apply();
                Snackbar.make(view, "Dumping shared preferences", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
