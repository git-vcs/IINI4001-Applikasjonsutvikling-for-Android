package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import androidx.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;


public class GridActivity extends AppCompatActivity {
    // the sum of a line, row or tile in sudoku
    final int sudokuSum = 1+2+3+4+5+6+7+8+9;
    // data er et brett sudoku
    ArrayList<int[]> data = new ArrayList<int[]>();
    Boolean newBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        try {
           data=(ArrayList<int[]>) intent.getExtras().get("board");
           newBoard=intent.getBooleanExtra("newBoard",false);
        }catch (Exception e){
            Log.i(getLocalClassName(), e.getMessage());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        if(data!=null&& !newBoard){
            loadBoard();
        }else {
            emptyBoard();
        }

    }
    private void emptyBoard(){
        //gjemmer ting som har med å lage nytt brett
        Spinner dropDown=(Spinner) findViewById(R.id.spinnerdifficulties);


        ArrayAdapter<String> spinnerList=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.difficultiesSpinner));
        dropDown.setAdapter(spinnerList);

        GridLayout board=findViewById(R.id.sudokugrid);
        Button sjekkButton=(Button)findViewById(R.id.buttonvalidate);
        sjekkButton.setVisibility(Button.GONE);
        for (int i = 0; i <board.getChildCount(); i++) {
            EditText temp=(EditText)board.getChildAt(i);
            temp.setText("");
            temp.setEnabled(true);
        }
        Log.i(getLocalClassName(), "emptyBoard: ");

    }

    private void loadBoard(){
        {
            Spinner dropDown=(Spinner) findViewById(R.id.spinnerdifficulties);
            dropDown.setEnabled(false);
            dropDown.setVisibility(Spinner.GONE);
            Button saveButton=findViewById(R.id.save);
            saveButton.setEnabled(false);
            saveButton.setVisibility(Button.GONE);
            EditText editText = (EditText)findViewById(R.id.editName);
            editText.setVisibility(EditText.GONE);
        }



        GridLayout board=findViewById(R.id.sudokugrid);
        for (int i = 0; i <board.getChildCount(); i++) {
            final EditText oneTile=(EditText)board.getChildAt(i);
            Log.i("laster data", " "+getResources().getResourceEntryName(oneTile.getId()).replaceAll("editTextNumber",""));
            char[] tempCharCoordinate=getResources().getResourceEntryName(oneTile.getId()).replaceAll("editTextNumber","").toCharArray();
            int[] yxCoordinate= {Integer.parseInt(tempCharCoordinate[0]+""),Integer.parseInt(tempCharCoordinate[1]+"")};

            String value = String.valueOf(data.get(yxCoordinate[1])[yxCoordinate[0]]);
            if (value.equals("-1") || value.equals("")){
                oneTile.setText("");
                oneTile.setEnabled(true);
                oneTile.setClickable(true);
                oneTile.setBackgroundColor(Color.LTGRAY);
                //Log press for å skifte farger
                oneTile.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View view) {
                        Drawable color=oneTile.getBackground();
                        Log.i(getLocalClassName(),"Current Color: "+((ColorDrawable)color).getColor() );
                        if(((ColorDrawable)color).getColor() == Color.LTGRAY){
                            view.setBackgroundColor(Color.YELLOW);
                        }else {
                            view.setBackgroundColor(Color.LTGRAY);
                        }
                        return true;
                    }
                });

            }else {
                // legger inn verdi hentet fra database
                // Låser verdien og setter fargen
                oneTile.setText(value);
                oneTile.setEnabled(false);
                oneTile.setBackgroundColor(Color.WHITE);
            }
        }


    }

    public void saveBoard(View v){
        Log.i(getLocalClassName(),"saveBoard");
        ArrayList<int[]> tiles = readBoardInt();
        boolean successfulSave=true;
        try{
            DatabaseManager databaseManager=new DatabaseManager(getBaseContext());
            int difficulty=((Spinner)findViewById(R.id.spinnerdifficulties)).getSelectedItemPosition();
            String name=((EditText)findViewById(R.id.editName)).getText().toString();
            Log.i(getLocalClassName(),"saveBoard, navn: "+name+" diff: "+difficulty );
            databaseManager.insertBoard(tiles,name,difficulty);

        }catch (Exception e){
            e.printStackTrace();
            successfulSave=false;
        }

        if(successfulSave){
            finish();
            startActivity(new Intent(getApplicationContext(),DifficultSelect.class));
        }

    }

    private ArrayList<int[]> readBoardInt(){
        //Laster dtata fra brett
        ArrayList<int[]> tiles = new ArrayList<>();
        GridLayout board=findViewById(R.id.sudokugrid);
        int pointer=0;
        for (int i = 0; i <9; i++) {
            int[] temp=new int[9];
            for (int j = 0; j < 9 ; j++) {
                Log.d("validate: ",i+" "+j);
                try{
                    int tempInt = Integer.parseInt(((EditText)board.getChildAt(pointer)).getText().toString());
                    if(tempInt>=1 && tempInt<=9){
                        temp[j]= tempInt;
                    }
                }catch (Exception e){
                    //tom rute
                    temp[j]=-1;
                }
                pointer++;
            }
            tiles.add(temp);

        }
        return tiles;
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

    private ArrayList<EditText[]> readBoard(){
        //Laster dtata fra brett
        ArrayList<EditText[]> tiles = new ArrayList<>();
        GridLayout board=findViewById(R.id.sudokugrid);
        int pointer=0;
        for (int i = 0; i <9; i++) {
            EditText[] temp=new EditText[9];
            for (int j = 0; j < 9 ; j++) {
                Log.d("validate: ",i+" "+j);
                temp[j]=((EditText) board.getChildAt(pointer));
                pointer++;
            }
            tiles.add(temp);

        }
        return tiles;

    }
    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }
    public void validate(View v){
        validateBoard();
    }

    private void validateBoard(){
        boolean block=true;
        boolean columnSum=true;
        boolean rowSum=true;
        boolean emptyTile=false;
        //Laster data fra brett og analyserer innholdet
        ArrayList<EditText[]> tiles = readBoard();

        // hvis vi går igjennom alle rader og finner feil er det ikke behov for å sjekke resten
        if(columnSum&&rowSum){
            //row sum
            for (int i = 0; i < tiles.size(); i++) {
                int sum=0;
                for (int j = 0; j <9 ; j++) {
                    try {
                        int temp=Integer.parseInt(tiles.get(i)[j].getText().toString());
                        if(temp>=1&& temp<=9){
                            sum+=temp;
                        }else throw new Exception();
                    }catch (Exception e){
                        tiles.get(i)[j].setBackgroundColor(Color.RED);
                        Toast.makeText(getBaseContext(),R.string.numberError,Toast.LENGTH_LONG).show();
                        emptyTile=true;
                    }
                }

                if(sum == sudokuSum && !emptyTile){
                    Log.i("validate", "sum ok rad: "+i+" Sum: "+sum);
                }else {
                    rowSum=false;
                    Log.i("validate","Sum IKKE ok rad: "+i+" Sum: "+sum);
                }

            }

        }



        //column sum

        if(columnSum&&rowSum){
            for (int i = 0; i < tiles.size(); i++) {
                int sum=0;
                for (int j = 0; j <9 ; j++) {
                    try {
                        int temp=Integer.parseInt(tiles.get(j)[i].getText().toString());
                        if(temp>=1&& temp<=9){
                            sum+=temp;
                        }else throw new Exception();

                    }catch (Exception e){
                        tiles.get(j)[i].setBackgroundColor(Color.RED);
                        Toast.makeText(getBaseContext(),R.string.numberError,Toast.LENGTH_LONG).show();
                        emptyTile=true;
                    }
                }
                if(sum==sudokuSum&&!emptyTile){
                    Log.i("validate", "sum ok column: "+i+" Sum: "+sum);
                }else Log.i("validate","Sum IKKE ok column: "+i+" Sum: "+sum);

            }
        }


        if(rowSum&&columnSum){
            //3x3 sum
            //hopper i x-akse
            int blockSum = 0;
            //block 0x0
            blockSum += Integer.parseInt(tiles.get(0)[0].getText().toString())+Integer.parseInt(tiles.get(0)[1].getText().toString())+Integer.parseInt(tiles.get(0)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(1)[0].getText().toString())+Integer.parseInt(tiles.get(1)[1].getText().toString())+Integer.parseInt(tiles.get(1)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(2)[0].getText().toString())+Integer.parseInt(tiles.get(2)[1].getText().toString())+Integer.parseInt(tiles.get(2)[2].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 0x1
            blockSum += Integer.parseInt(tiles.get(0)[3].getText().toString())+Integer.parseInt(tiles.get(0)[4].getText().toString())+Integer.parseInt(tiles.get(0)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(1)[3].getText().toString())+Integer.parseInt(tiles.get(1)[4].getText().toString())+Integer.parseInt(tiles.get(1)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(2)[3].getText().toString())+Integer.parseInt(tiles.get(2)[4].getText().toString())+Integer.parseInt(tiles.get(2)[5].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 0x2
            blockSum += Integer.parseInt(tiles.get(0)[6].getText().toString())+Integer.parseInt(tiles.get(0)[7].getText().toString())+Integer.parseInt(tiles.get(0)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(1)[6].getText().toString())+Integer.parseInt(tiles.get(1)[7].getText().toString())+Integer.parseInt(tiles.get(1)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(2)[6].getText().toString())+Integer.parseInt(tiles.get(2)[7].getText().toString())+Integer.parseInt(tiles.get(2)[8].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;
            Log.i("3x3 Sum ","1x0");
            //block 1x0
            blockSum += Integer.parseInt(tiles.get(3)[0].getText().toString())+Integer.parseInt(tiles.get(3)[1].getText().toString())+Integer.parseInt(tiles.get(3)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(4)[0].getText().toString())+Integer.parseInt(tiles.get(4)[1].getText().toString())+Integer.parseInt(tiles.get(4)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(5)[0].getText().toString())+Integer.parseInt(tiles.get(5)[1].getText().toString())+Integer.parseInt(tiles.get(5)[2].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 1x1
            Log.i("3x3 Sum ","1x1");
            blockSum += Integer.parseInt(tiles.get(3)[3].getText().toString())+Integer.parseInt(tiles.get(3)[4].getText().toString())+Integer.parseInt(tiles.get(3)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(4)[3].getText().toString())+Integer.parseInt(tiles.get(4)[4].getText().toString())+Integer.parseInt(tiles.get(4)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(5)[3].getText().toString())+Integer.parseInt(tiles.get(5)[4].getText().toString())+Integer.parseInt(tiles.get(5)[5].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 1x2
            Log.i("3x3 Sum ","1x2");
            blockSum += Integer.parseInt(tiles.get(3)[6].getText().toString())+Integer.parseInt(tiles.get(3)[7].getText().toString())+Integer.parseInt(tiles.get(3)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(4)[6].getText().toString())+Integer.parseInt(tiles.get(4)[7].getText().toString())+Integer.parseInt(tiles.get(4)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(5)[6].getText().toString())+Integer.parseInt(tiles.get(5)[7].getText().toString())+Integer.parseInt(tiles.get(5)[8].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 2x0
            Log.i("3x3 Sum ","2x0");
            blockSum += Integer.parseInt(tiles.get(6)[0].getText().toString())+Integer.parseInt(tiles.get(6)[1].getText().toString())+Integer.parseInt(tiles.get(6)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(7)[0].getText().toString())+Integer.parseInt(tiles.get(7)[1].getText().toString())+Integer.parseInt(tiles.get(7)[2].getText().toString());
            blockSum += Integer.parseInt(tiles.get(8)[0].getText().toString())+Integer.parseInt(tiles.get(8)[1].getText().toString())+Integer.parseInt(tiles.get(8)[2].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 2x1
            Log.i("3x3 Sum ","2x1");
            blockSum += Integer.parseInt(tiles.get(6)[3].getText().toString())+Integer.parseInt(tiles.get(6)[4].getText().toString())+Integer.parseInt(tiles.get(6)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(7)[3].getText().toString())+Integer.parseInt(tiles.get(7)[4].getText().toString())+Integer.parseInt(tiles.get(7)[5].getText().toString());
            blockSum += Integer.parseInt(tiles.get(8)[3].getText().toString())+Integer.parseInt(tiles.get(8)[4].getText().toString())+Integer.parseInt(tiles.get(8)[5].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;

            //block 2x2
            Log.i("3x3 Sum ","2x2");
            blockSum += Integer.parseInt(tiles.get(6)[6].getText().toString())+Integer.parseInt(tiles.get(6)[7].getText().toString())+Integer.parseInt(tiles.get(6)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(7)[6].getText().toString())+Integer.parseInt(tiles.get(7)[7].getText().toString())+Integer.parseInt(tiles.get(7)[8].getText().toString());
            blockSum += Integer.parseInt(tiles.get(8)[6].getText().toString())+Integer.parseInt(tiles.get(8)[7].getText().toString())+Integer.parseInt(tiles.get(8)[8].getText().toString());
            if(blockSum==sudokuSum &&!emptyTile){
                Log.i("3x3 Sum ",blockSum+"");
            }else{
                Log.i("3x3 Sum ",blockSum+"");
                block=false;
            }
            blockSum=0;
        }



        if(columnSum&&rowSum&&block){
            Toast.makeText(getBaseContext(),R.string.correctSudoku,Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(getBaseContext(),R.string.boardNotCorrect,Toast.LENGTH_LONG).show();
        }
    }




    
}