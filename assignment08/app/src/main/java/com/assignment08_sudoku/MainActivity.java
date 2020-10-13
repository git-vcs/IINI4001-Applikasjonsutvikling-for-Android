package com.assignment08_sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
    PreferenceScreen og innstillinger i denne øvingen er innspirert av følgende videoer
    https://www.youtube.com/watch?v=SfeRakSWWbk
    https://www.youtube.com/watch?v=buFD0HI2F14
 */



public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedColor=sharedPreferences.getString(getString(R.string.current_color),"#FFfcfcfc");
        ConstraintLayout constraintLayout= (ConstraintLayout) findViewById(R.id.activity_main);
        if (constraintLayout!=null){
            constraintLayout.setBackgroundColor(Color.parseColor(selectedColor));

        }else Log.i("main", "onCreate: layout=NULL!!!!");
    }

    @Override
    protected void onResume() {
        ArrayList<String> res =null;
        super.onResume();

        //henter data fra database
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            Log.i("main onRsume", "Current setting: "+ sharedPreferences.getString(getString(R.string.current_settings_visning_codes),"-1"));

            switch (sharedPreferences.getString(getString(R.string.current_settings_visning_codes),"0")){

                case "0":
                    //vis all info

                    break;
                case "1":
                    // vis alle forfattere

                    break;
                case "2":
                    //vis alle bøker

                    break;


            }
            //showResults(res);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //metoden som forandrer listen av bøker
    public void showResults(ArrayList<String> list) {

    }


    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }

    //lager en fil lokalt

    /*
    private void makeDataFile(String fileName){
        File dir=getFilesDir();
        File file=new File(dir,fileName);
        try {
            PrintWriter skriver=new PrintWriter(file);
            for (int i = 0; i <data.length ; i+=2) {
                skriver.println(data[i]+","+data[i+1]);
            }
            skriver.close();
        }catch (Exception e){
            Log.i("Exseption skriving: ",e.getMessage());
        }

    }


     */
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


    //leser fra lokal fil
   /*
    private void readFiles(String fileName){
        try {
            lestFil= new ArrayList<String>();

            File dir = getFilesDir();
            File file=new File(dir,fileName);
            FileReader leser=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(leser);
            String line=bufferedReader.readLine();
            while (line!=null){
                lestFil.add(line);
                line=bufferedReader.readLine();
            }
            bufferedReader.close();
            leser.close();
        }catch (Exception e){
            Log.i("readFiles",e.getMessage());
        }

    }

    */

   /*
    private void writeToDataBase(){
        try {
            db=new DatabaseManager(getBaseContext());
            db.clean();
            for (int i = 0; i <lestFil.size(); i++) {
                String enLinje[] = lestFil.get(i).split(",");
                Exception tableSizeExseption = null;
                //sjekker at tabellen er delt i 2 på forfatter og boktittel
                if(enLinje.length==2){
                    db.insert(enLinje[0],enLinje[1]);
                }else throw tableSizeExseption;
            }

        }catch (Exception e){
            Log.i("writeToDataBase",e.getMessage());
        }
    }


    */





}