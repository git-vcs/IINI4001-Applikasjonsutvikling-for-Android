package com.assignment08_sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import androidx.core.app.TaskStackBuilder;
import androidx.preference.PreferenceManager;
import java.util.Locale;

public class settingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener(){
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            String newLanguageCode = sharedPreferences.getString(getString(R.string.current_language_code),"");
            Log.i(getLocalClassName(),"onSharedPreferenceChanged s: "+s+"  new language: "+newLanguageCode);
            Locale locale = new Locale(newLanguageCode);
            Configuration configuration=new Configuration();
            configuration.setLocale(locale);
            Resources resources=getResources();
            resources.updateConfiguration(configuration,resources.getDisplayMetrics());

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getSimpleName(),"OnCreate");
        setContentView(R.layout.activity_settings);
        if(findViewById(R.id.fragment_settings)!=null){
            //legger til fragmentet med innstillingene.
            getFragmentManager().beginTransaction().add(R.id.fragment_settings,new SettingsFragment()).commit();

        }else Log.i(this.getClass().getSimpleName(), "onCreate: fant ikke fragment_settings");
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);

    }




    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
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
        finish();
       // startActivity(new Intent(getApplicationContext(),DifficultSelect.class));

    }

    public void settings(View v){

    }

    public void reset(View v){
        /*
        Tatt inspirasjon fra følgende nettside for advarsels dialogen
        https://www.tutorialspoint.com/android/android_alert_dialoges.htm
         */
        AlertDialog.Builder warning = new AlertDialog.Builder(this);
        warning.setTitle(R.string.dataBaseMessage);
        warning.setPositiveButton(getString(R.string.yes),new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                sharedPreferences.edit().putBoolean("resetDatabase",true).apply();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        warning.setNegativeButton(getString(R.string.no),new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        warning.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getLocalClassName(),"onDestroy");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(getLocalClassName(),"onPostResume");
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getLocalClassName(),"onPause");
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(null);
    }
}