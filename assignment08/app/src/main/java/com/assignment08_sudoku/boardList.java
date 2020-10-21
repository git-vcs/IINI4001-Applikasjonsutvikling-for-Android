package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class boardList extends AppCompatActivity {
    ArrayList<int []> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

    }

    public void backButton(View v){
        finish();
    }

    public void launchBoard(View view){
        data.add(new int[]{9,6,8,1,3,5,2,4,7});
        data.add(new int[]{1,3,7,8,4,2,9,5,6});
        data.add(new int[]{4,2,5,9,6,7,3,8,1});
        data.add(new int[]{7,8,2,6,1,3,4,9,5});
        data.add(new int[]{3,1,4,5,9,8,7,6,2});
        data.add(new int[]{5,9,6,2,7,4,8,1,3});
        data.add(new int[]{8,7,9,3,5,1,6,2,4});
        data.add(new int[]{6,4,1,7,2,9,5,3,8});
        data.add(new int[]{2,5,3,4,8,6,1,7,9});
        Intent intent =new Intent(this,GridActivity.class);
        intent.putExtra("board",data);
        startActivityForResult(intent,1);


    }
}