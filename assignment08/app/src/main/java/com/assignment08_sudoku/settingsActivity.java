package com.assignment08_sudoku;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;

public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getSimpleName(),"OnCreate");
        setContentView(R.layout.activity_settings);
        if(findViewById(R.id.fragment_settings)!=null){

            if(savedInstanceState!=null){
                return;

            }else Log.i(this.getClass().getSimpleName(), "onCreate: fant ikke savednstance");
            //legger til fragmentet med innstillingene.
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
        // går tilbake til startsiden
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}