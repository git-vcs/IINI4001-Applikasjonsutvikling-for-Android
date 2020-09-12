package com.example.assignement02task2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class rnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int max=10;
        Intent result=getIntent();
        try {
            max=(int)result.getExtras().get("max");
        }catch (Exception e){
            Log.i("RND",e.toString());
        }
        Log.i("RND-onCreate","Lasta");
        Random rndInt=new Random();
        Log.i("RND-onCreate-max",result.getIntExtra("max",69)+"");
        result.putExtra("rnd01",rndInt.nextInt(max));
        result.putExtra("rnd02",rndInt.nextInt(max));
        setResult(Activity.RESULT_OK,result);
        super.onCreate(savedInstanceState);
        finish();
    }
}