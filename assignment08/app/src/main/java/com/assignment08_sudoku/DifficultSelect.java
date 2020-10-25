package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DifficultSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficultselect);

    }

    public void easyList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",1);
        startActivityForResult(intent,1);
    }

    public void midList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",2);
        startActivityForResult(intent,1);
    }

    public void hardList(View v){
        Intent intent =new Intent(this,boardList.class);
        intent.putExtra("difficulty",3);
        startActivityForResult(intent,1);
    }





}