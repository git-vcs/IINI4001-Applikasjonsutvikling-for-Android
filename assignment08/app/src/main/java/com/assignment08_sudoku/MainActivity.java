package com.assignment08_sudoku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/*
    PreferenceScreen og innstillinger i denne øvingen er innspirert av følgende videoer
    https://www.youtube.com/watch?v=SfeRakSWWbk
    https://www.youtube.com/watch?v=buFD0HI2F14
 */



public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseManager db;




    public void testButton(View v){
        try {
    
            try {


            } catch (Exception e) {
                e.printStackTrace();
            }


        }catch (Exception e){
            Log.i("TEST",e.getMessage());
            e.printStackTrace();

        }

    }

    @Override
    protected void onStart() {
        ArrayList<int[]> data=new ArrayList<>();
        super.onStart();
        Log.i(getLocalClassName(),"onStart");
        try {
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
            data.add(new int[]{8,2,7,1,5,4,3,9,6});
            data.add(new int[]{9,6,5,3,2,7,1,4,8});
            data.add(new int[]{3,4,1,6,8,9,7,5,2});
            data.add(new int[]{5,9,3,4,6,8,2,7,1});
            data.add(new int[]{4,7,2,5,1,3,6,8,9});
            data.add(new int[]{6,1,8,9,7,2,4,3,5});
            data.add(new int[]{7,8,6,2,3,5,9,1,4});
            data.add(new int[]{1,5,4,7,9,6,8,2,3});
            data.add(new int[]{2,3,9,8,4,1,5,6,7});
            db.insertBoard(data,"Denne er VELDGI vanskelig Ferdig",2);






        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedColor=sharedPreferences.getString(getString(R.string.current_color),"#FFfcfcfc");
        ConstraintLayout constraintLayout= (ConstraintLayout) findViewById(R.id.activity_main);
        if (constraintLayout!=null){
            //constraintLayout.setBackgroundColor(Color.parseColor(selectedColor));

        }else Log.i("main", "onCreate: layout=NULL!!!!");
    }

    public void oneStartGame(View v) {
        startActivity(new Intent(getApplicationContext(),DifficultSelect.class));
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