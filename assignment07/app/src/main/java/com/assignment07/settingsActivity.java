package com.assignment07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
// PreferenceActivity
public class settingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getSimpleName(),"OnCreate");
        setContentView(R.layout.activity_settings);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("test fra settings activity");
        if(findViewById(R.id.fragment_settings)!=null){
            if(savedInstanceState!=null){
                return;

            }else Log.i(this.getClass().getSimpleName(), "onCreate: fant ikke savednstance");
            getFragmentManager().beginTransaction().add(R.id.fragment_settings,new SettingsFragment()).commit();

        }else Log.i(this.getClass().getSimpleName(), "onCreate: fant ikke fragment_settings");
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        Log.i(this.getClass().getSimpleName(),"onOptionsMenuClosed");
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
        Log.i(this.getClass().getSimpleName(),"onContextMenuClosed");
    }

    @Override
    public void closeContextMenu() {
        super.closeContextMenu();
        Log.i(this.getClass().getSimpleName(),"closeContextMenu");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(this.getClass().getSimpleName(),"onBackPressed");
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}