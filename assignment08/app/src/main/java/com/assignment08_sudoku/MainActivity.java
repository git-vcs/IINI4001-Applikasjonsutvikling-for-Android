package com.assignment08_sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Locale;

/*
    PreferenceScreen og innstillinger i denne øvingen er innspirert av følgende videoer
    https://www.youtube.com/watch?v=SfeRakSWWbk
    https://www.youtube.com/watch?v=buFD0HI2F14
 */



public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseManager db;


    public void howTo(View v){
        startActivity(new Intent(getApplicationContext(),Instructions.class));

    }


    @Override
    protected void onStart() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean reset = sharedPreferences.getBoolean("resetDatabase",true);

        String newLanguageCode = sharedPreferences.getString(getString(R.string.current_language_code),"");
        Log.i(getLocalClassName(),"onStart   language: "+newLanguageCode);
        Locale locale = new Locale(newLanguageCode);
        Configuration configuration=new Configuration();
        configuration.setLocale(locale);
        Resources resources=getResources();
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());


        ArrayList<int[]> data=new ArrayList<>();
        super.onStart();
        Log.i(getLocalClassName(),"onStart");
        try {
            if(reset){
                DatabaseManager db = new DatabaseManager(getBaseContext());
                db.clean();
                data.add(new int[]{9,6,8,1,3,5,2,4,7});
                data.add(new int[]{1,3,7,8,4,2,9,5,6});
                data.add(new int[]{4,2,5,9,6,7,3,8,1});
                data.add(new int[]{7,8,2,6,1,3,4,9,5});
                data.add(new int[]{3,1,4,5,9,8,7,6,2});
                data.add(new int[]{5,9,6,2,7,4,8,1,3});
                data.add(new int[]{8,7,9,3,5,1,6,2,4});
                data.add(new int[]{6,4,1,7,2,9,5,3,8});
                data.add(new int[]{2,5,3,4,8,6,1,7,9});
                db.insertBoard(data,"Lett brett Ferdig",0);
                data=new ArrayList<>();

                data.add(new int[]{9,-1,8,1,3,5,2,4,7});
                data.add(new int[]{1,3,7,8,4,2,9,5,6});
                data.add(new int[]{4,2,-1,9,6,7,3,8,1});
                data.add(new int[]{7,8,2,-1,1,3,4,9,5});
                data.add(new int[]{3,1,-1,5,9,8,7,6,2});
                data.add(new int[]{5,9,6,2,7,4,8,1,3});
                data.add(new int[]{8,7,9,3,5,1,6,2,4});
                data.add(new int[]{6,-1,1,7,2,9,5,3,8});
                data.add(new int[]{2,5,3,4,8,6,1,7,9});
                db.insertBoard(data,"Lett brett uløst",0);

                data=new ArrayList<>();

                data.add(new int[]{1,4,6,7,9,2,3,8,5});
                data.add(new int[]{2,5,8,3,4,6,7,9,1});
                data.add(new int[]{3,7,9,5,8,1,4,6,2});
                data.add(new int[]{4,3,7,9,1,5,8,2,6});
                data.add(new int[]{5,8,1,6,2,7,9,3,4});
                data.add(new int[]{6,9,2,4,3,8,1,5,7});
                data.add(new int[]{7,1,3,2,6,9,5,4,8});
                data.add(new int[]{8,2,4,1,5,3,6,7,9});
                data.add(new int[]{9,6,5,8,7,4,2,1,3});
                db.insertBoard(data,"Middels brett Ferdig",1);

                data=new ArrayList<>();
                data.add(new int[]{1,-1,6,7,9,2,3,8,5});
                data.add(new int[]{2,5,8,-1,4,-1,7,9,1});
                data.add(new int[]{3,7,9,-1,8,1,4,6,2});
                data.add(new int[]{4,3,7,9,1,5,8,2,6});
                data.add(new int[]{5,8,1,6,2,7,-1,3,4});
                data.add(new int[]{6,9,2,-1,3,8,1,5,7});
                data.add(new int[]{7,1,3,2,6,9,5,-1,8});
                data.add(new int[]{8,2,4,1,-1,3,6,7,9});
                data.add(new int[]{9,6,5,8,7,4,2,1,3});
                db.insertBoard(data,"Middels brett uferdig",1);

                data=new ArrayList<>();
                data.add(new int[]{8,2,7,1,5,4,3,9,6});
                data.add(new int[]{9,6,5,3,2,7,1,4,8});
                data.add(new int[]{3,4,1,6,8,9,7,5,2});
                data.add(new int[]{5,9,3,4,6,8,2,7,1});
                data.add(new int[]{4,7,2,5,1,3,6,8,9});
                data.add(new int[]{6,1,8,9,7,2,4,3,5});
                data.add(new int[]{7,8,6,2,3,5,9,1,4});
                data.add(new int[]{1,5,4,7,9,6,8,2,3});
                data.add(new int[]{2,3,9,8,4,1,5,6,7});
                db.insertBoard(data,"Denne er VELDIG vanskelig Ferdig",2);

                data=new ArrayList<>();
                data.add(new int[]{-1,2,7,-1,5,4,3,9,-1});
                data.add(new int[]{9,6,-1,3,2,-1,1,-1,8});
                data.add(new int[]{3,4,1,6,-1,9,7,5,2});
                data.add(new int[]{5,9,3,4,-1,8,2,7,1});
                data.add(new int[]{4,7,2,5,1,3,6,8,9});
                data.add(new int[]{-1,1,8,-1,7,-1,4,3,-1});
                data.add(new int[]{7,8,6,2,3,5,9,1,4});
                data.add(new int[]{1,5,4,7,9,6,8,2,-1});
                data.add(new int[]{2,3,9,8,4,-1,5,6,7});
                db.insertBoard(data,"Denne er VELDIG vanskelig uferdig",2);
            }// end of if
            sharedPreferences.edit().putBoolean("resetDatabase",false).apply();





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void oneStartGame(View v) {
        startActivity(new Intent(getApplicationContext(),DifficultSelect.class));
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newLanguageCode = sharedPreferences.getString(getString(R.string.current_language_code),"");
        Log.i(getLocalClassName(),"onResume   new language: "+newLanguageCode);
        Locale locale = new Locale(newLanguageCode);
        Configuration configuration=new Configuration();
        configuration.setLocale(locale);
        Resources resources=getResources();
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        super.onResume();

    }




    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("onOptionsItemSelected", (String.valueOf(item.getItemId())));
        switch (item.getItemId()){
            case R.id.activity_settings:
                startActivity(new Intent(getApplicationContext(),settingsActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}