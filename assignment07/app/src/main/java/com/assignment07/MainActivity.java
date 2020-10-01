package com.assignment07;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
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
        writeToDataBase();
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