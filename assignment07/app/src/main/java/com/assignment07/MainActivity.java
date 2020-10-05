package com.assignment07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private DatabaseManager db;
String data[]={
        "Bud Kurniawan","Android Application Development: A Beginners Tutorioal",
        "Mildrid Ljosland", "Programmering i C++",
        "Else Lervik", "Programmering i C++",
        "Mildrid Ljosland", "Algoritmer og datastrukturer",
        "Helge Hafting", "Algoritmer og datastrukturer"};
ArrayList<String> lestFil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeDataFile("test");
        readFiles("test");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        writeToDataBase();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedColor=sharedPreferences.getString(getString(R.string.current_color),"#FFfcfcfc");
        ConstraintLayout constraintLayout= (ConstraintLayout) findViewById(R.id.activity_main);
        if (constraintLayout!=null){
            constraintLayout.setBackgroundColor(Color.parseColor(selectedColor));

        }else Log.i("main", "onCreate: layout=NULL!!!!");

        ArrayList<String> res = db.getAllBooks();
        showResults(res);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;

    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = (TextView)findViewById(R.id.main_tekst);
        t.setText(res);
    }


    public void settings(View v){
       //
        // setContentView(R.layout.activity_settings,settingsActivity.class);

        startActivity(new Intent(this,settingsActivity.class));

    }

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






}