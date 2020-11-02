package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Locale;

public class DifficultSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(getLocalClassName(),getResources().getConfiguration().locale.toString());
        setContentView(R.layout.activity_difficultselect);

    }

    public void easyList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",0);
        startActivityForResult(intent,1);
    }

    public void midList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",1);
        startActivityForResult(intent,1);
    }

    public void hardList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",2);
        startActivityForResult(intent,1);
    }
    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }

    @Override
    protected void onResume() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newLanguageCode = sharedPreferences.getString(getString(R.string.current_language_code), "");
        Log.i(getLocalClassName(), "onPostResume   new language: " + newLanguageCode);
        Locale locale = new Locale(newLanguageCode);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        Resources resources = getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        super.onResume();
    }



}