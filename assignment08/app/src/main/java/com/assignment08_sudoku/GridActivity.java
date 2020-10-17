package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.gridlayout.widget.GridLayout;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {
    // the sum of a line, row or tile in sudoku
    final int sudokuSum = 1+2+3+4+5+6+7+8+9;
    ArrayList<int[]> data = new ArrayList<int[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        loadBoard();

        /*
        //Debug print
         GridLayout board=findViewById(R.id.sudokugrid);
        for (int i = 0; i <board.getChildCount(); i++) {
            EditText temp=(EditText)board.getChildAt(i);
            Log.i("laster data", " "+getResources().getResourceEntryName(temp.getId()).replaceAll("editTextNumber",""));

            temp.setText(getResources().getResourceEntryName(temp.getId()).replaceAll("editTextNumber",""));
        }
        Log.i(getLocalClassName(), "onCreate: ");
         */

    }
    
    //todo lag innlesning av brett
    private void loadBoard(){

        data.add(new int[]{7,3,5,6,1,4,8,9,2});
        data.add(new int[]{8,4,2,9,7,3,5,6,1});
        data.add(new int[]{9,4,2,9,7,3,5,6,1});
        data.add(new int[]{2,8,6,3,4,9,1,5,7});
        data.add(new int[]{4,1,3,8,5,7,9,2,6});
        data.add(new int[]{5,7,9,1,2,6,4,3,8});
        data.add(new int[]{1,5,7,4,9,2,6,8,3});
        data.add(new int[]{6,9,4,7,3,8,2,1,5});
        data.add(new int[]{3,2,8,5,6,1,7,4,9});


        /*
          data.add(new int[]{1,4,-1,5,-1,6,3,-1,-1});
        data.add(new int[]{3,-1,-1,-1,-1,-1,-1,8,-1});
        data.add(new int[]{9,8,2,4,1,3,-1,-1,-1});
        data.add(new int[]{-1,-1,-1,8,-1,-1,-1,-1,9});
        data.add(new int[]{-1,7,6,3,-1,-1,1,2,-1});
        data.add(new int[]{8,-1,-1,-1,-1,1,2,-1,-1});
        data.add(new int[]{-1,-1,-1,2,3,7,8,1,5});
        data.add(new int[]{-1,5,-1,-1,-1,-1,-1,-1,6});
        data.add(new int[]{-1,-1,8,6,-1,5,-1,3,4});

         */



        GridLayout board=findViewById(R.id.sudokugrid);
        for (int i = 0; i <board.getChildCount(); i++) {
            EditText oneTile=(EditText)board.getChildAt(i);
            Log.i("laster data", " "+getResources().getResourceEntryName(oneTile.getId()).replaceAll("editTextNumber",""));
            char[] tempCharCoordinate=getResources().getResourceEntryName(oneTile.getId()).replaceAll("editTextNumber","").toCharArray();
            int[] yxCoordinate= {Integer.parseInt(tempCharCoordinate[0]+""),Integer.parseInt(tempCharCoordinate[1]+"")};
            int[] debug =data.get(yxCoordinate[1]);
            String valiue=String.valueOf(data.get(yxCoordinate[1])[yxCoordinate[0]]);
            if (valiue.equals("-1")||valiue.equals("")){
                oneTile.setText("");
                oneTile.setEnabled(true);
            }else {
                oneTile.setText(valiue);
                oneTile.setEnabled(false);
            }
        }


    }


    public void validate(View v){
        validateBoard();
    }

    private void validateBoard(){
        ArrayList<EditText[]> tiles = new ArrayList<>();
        GridLayout board=findViewById(R.id.sudokugrid);
        int pointer=0;
        for (int i = 0; i <9; i++) {
            EditText[] temp=new EditText[9];
            for (int j = 0; j < 9 ; j++) {
                Log.d("valitade: ",i+" "+j);
                temp[j]=((EditText) board.getChildAt(pointer));
                pointer++;
            }
            tiles.add(temp);

        }


    }




    
}