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

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String newLanguageCode = sharedPreferences.getString(getString(R.string.current_language_code),"");
        Log.i(getLocalClassName(),"onPostResume   new language: "+newLanguageCode);
        Locale locale = new Locale(newLanguageCode);
        Configuration configuration=new Configuration();
        configuration.setLocale(locale);
        Resources resources=getResources();
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

    }

    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }

}