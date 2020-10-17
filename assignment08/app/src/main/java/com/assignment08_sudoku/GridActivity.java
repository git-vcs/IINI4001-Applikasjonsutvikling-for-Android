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


        //Debug print
        /*
         GridLayout board=findViewById(R.id.sudokugrid);
        for (int i = 0; i <board.getChildCount(); i++) {
            EditText temp=(EditText)board.getChildAt(i);
            //Log.i("laster data", " "+getResources().getResourceEntryName(temp.getId()).replaceAll("editTextNumber",""));

            temp.setText(getResources().getResourceEntryName(temp.getId()).replaceAll("editTextNumber",""));
            //temp.setText(i+"");

        }
        Log.i(getLocalClassName(), "onCreate: ");


         */



    }
    
    //todo lag innlesning av brett
    private void loadBoard(){

        //sovled eksample
        data.add(new int[]{9,6,8,1,3,5,2,4,7});
        data.add(new int[]{1,3,7,8,4,2,9,5,6});
        data.add(new int[]{4,2,5,9,6,7,3,8,1});
        data.add(new int[]{7,8,2,6,1,3,4,9,5});
        data.add(new int[]{3,1,4,5,9,8,7,6,2});
        data.add(new int[]{5,9,6,2,7,4,8,1,3});
        data.add(new int[]{8,7,9,3,5,1,6,2,4});
        data.add(new int[]{6,4,1,7,2,9,5,3,8});
        data.add(new int[]{2,5,3,4,8,6,1,7,9});







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
            String valiue = String.valueOf(data.get(yxCoordinate[1])[yxCoordinate[0]]);
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
        boolean emptyTile=false;
        ArrayList<EditText[]> tiles = new ArrayList<>();
        GridLayout board=findViewById(R.id.sudokugrid);
        int pointer=0;
        for (int i = 0; i <9; i++) {
            EditText[] temp=new EditText[9];
            for (int j = 0; j < 9 ; j++) {
                Log.d("valitade: ",i+" "+j);
                temp[j]=((EditText) board.getChildAt(pointer));
                temp[j].setEnabled(true);
                pointer++;
            }
            tiles.add(temp);

        }

        //row sum
        for (int i = 0; i < tiles.size(); i++) {
            int sum=0;
            for (int j = 0; j <9 ; j++) {
                try {
                    sum+=Integer.parseInt(tiles.get(i)[j].getText().toString());
                    tiles.get(i)[j].setBackgroundColor(Color.WHITE);
                }catch (Exception e){
                    tiles.get(i)[j].setBackgroundColor(Color.RED);
                    emptyTile=true;
                }


            }
            if(sum == sudokuSum && !emptyTile){
                Log.i("valitade", "sum ok rad: "+i+" Sum: "+sum);
            }else Log.i("valitade","Sum IKKE ok rad: "+i+" Sum: "+sum);

        }


        //colum sum
        for (int i = 0; i < tiles.size(); i++) {
            int sum=0;
            for (int j = 0; j <9 ; j++) {
                try {
                    sum+=Integer.parseInt(tiles.get(j)[i].getText().toString());
                    tiles.get(j)[i].setBackgroundColor(Color.WHITE);
                }catch (Exception e){
                    tiles.get(j)[i].setBackgroundColor(Color.RED);
                    emptyTile=true;
                }
            }
            if(sum==sudokuSum&&!emptyTile){
                Log.i("valitade", "sum ok colum: "+i+" Sum: "+sum);
            }else Log.i("valitade","Sum IKKE ok colum: "+i+" Sum: "+sum);

        }

        //3x3 sum
        //hoppder i x-akse
        int blockSum = 0;


        //block 0x0
        blockSum += Integer.parseInt(tiles.get(0)[0].getText().toString())+Integer.parseInt(tiles.get(0)[1].getText().toString())+Integer.parseInt(tiles.get(0)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(1)[0].getText().toString())+Integer.parseInt(tiles.get(1)[1].getText().toString())+Integer.parseInt(tiles.get(1)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(2)[0].getText().toString())+Integer.parseInt(tiles.get(2)[1].getText().toString())+Integer.parseInt(tiles.get(2)[2].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 0x1
        blockSum += Integer.parseInt(tiles.get(0)[3].getText().toString())+Integer.parseInt(tiles.get(0)[4].getText().toString())+Integer.parseInt(tiles.get(0)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(1)[3].getText().toString())+Integer.parseInt(tiles.get(1)[4].getText().toString())+Integer.parseInt(tiles.get(1)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(2)[3].getText().toString())+Integer.parseInt(tiles.get(2)[4].getText().toString())+Integer.parseInt(tiles.get(2)[5].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 0x2
        blockSum += Integer.parseInt(tiles.get(0)[6].getText().toString())+Integer.parseInt(tiles.get(0)[7].getText().toString())+Integer.parseInt(tiles.get(0)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(1)[6].getText().toString())+Integer.parseInt(tiles.get(1)[7].getText().toString())+Integer.parseInt(tiles.get(1)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(2)[6].getText().toString())+Integer.parseInt(tiles.get(2)[7].getText().toString())+Integer.parseInt(tiles.get(2)[8].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;
        Log.i("3x3 Sum ","1x0");
        //block 1x0
        blockSum += Integer.parseInt(tiles.get(3)[0].getText().toString())+Integer.parseInt(tiles.get(3)[1].getText().toString())+Integer.parseInt(tiles.get(3)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(4)[0].getText().toString())+Integer.parseInt(tiles.get(4)[1].getText().toString())+Integer.parseInt(tiles.get(4)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(5)[0].getText().toString())+Integer.parseInt(tiles.get(5)[1].getText().toString())+Integer.parseInt(tiles.get(5)[2].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 1x1
        Log.i("3x3 Sum ","1x1");
        blockSum += Integer.parseInt(tiles.get(3)[3].getText().toString())+Integer.parseInt(tiles.get(3)[4].getText().toString())+Integer.parseInt(tiles.get(3)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(4)[3].getText().toString())+Integer.parseInt(tiles.get(4)[4].getText().toString())+Integer.parseInt(tiles.get(4)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(5)[3].getText().toString())+Integer.parseInt(tiles.get(5)[4].getText().toString())+Integer.parseInt(tiles.get(5)[5].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 1x2
        Log.i("3x3 Sum ","1x2");
        blockSum += Integer.parseInt(tiles.get(3)[6].getText().toString())+Integer.parseInt(tiles.get(3)[7].getText().toString())+Integer.parseInt(tiles.get(3)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(4)[6].getText().toString())+Integer.parseInt(tiles.get(4)[7].getText().toString())+Integer.parseInt(tiles.get(4)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(5)[6].getText().toString())+Integer.parseInt(tiles.get(5)[7].getText().toString())+Integer.parseInt(tiles.get(5)[8].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 2x0
        Log.i("3x3 Sum ","2x0");
        blockSum += Integer.parseInt(tiles.get(6)[0].getText().toString())+Integer.parseInt(tiles.get(6)[1].getText().toString())+Integer.parseInt(tiles.get(6)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(7)[0].getText().toString())+Integer.parseInt(tiles.get(7)[1].getText().toString())+Integer.parseInt(tiles.get(7)[2].getText().toString());
        blockSum += Integer.parseInt(tiles.get(8)[0].getText().toString())+Integer.parseInt(tiles.get(8)[1].getText().toString())+Integer.parseInt(tiles.get(8)[2].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 2x1
        Log.i("3x3 Sum ","2x1");
        blockSum += Integer.parseInt(tiles.get(6)[3].getText().toString())+Integer.parseInt(tiles.get(6)[4].getText().toString())+Integer.parseInt(tiles.get(6)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(7)[3].getText().toString())+Integer.parseInt(tiles.get(7)[4].getText().toString())+Integer.parseInt(tiles.get(7)[5].getText().toString());
        blockSum += Integer.parseInt(tiles.get(8)[3].getText().toString())+Integer.parseInt(tiles.get(8)[4].getText().toString())+Integer.parseInt(tiles.get(8)[5].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;

        //block 2x2
        Log.i("3x3 Sum ","2x2");
        blockSum += Integer.parseInt(tiles.get(6)[6].getText().toString())+Integer.parseInt(tiles.get(6)[7].getText().toString())+Integer.parseInt(tiles.get(6)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(7)[6].getText().toString())+Integer.parseInt(tiles.get(7)[7].getText().toString())+Integer.parseInt(tiles.get(7)[8].getText().toString());
        blockSum += Integer.parseInt(tiles.get(8)[6].getText().toString())+Integer.parseInt(tiles.get(8)[7].getText().toString())+Integer.parseInt(tiles.get(8)[8].getText().toString());
        if(blockSum==sudokuSum){
            Log.i("3x3 Sum ",blockSum+"");
        }else{
            Log.i("3x3 Sum ",blockSum+"");
        }
        blockSum=0;


    }




    
}