package com.assignment08_sudoku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class boardList extends AppCompatActivity {

    private int difficulty=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        try {
            //oppkoblink til sql-database for å hente en lista å brettnavn
            DatabaseManager db=new DatabaseManager(getBaseContext());
            Intent intent=getIntent();
            Log.i(getLocalClassName(),"onCreate difficulty: "+intent.getExtras().get("difficulty"));
           difficulty= Integer.parseInt(intent.getExtras().get("difficulty").toString());
           Log.i(getLocalClassName(),"onCreate difficulty: "+intent.getExtras().get("difficulty"));
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1);
            ArrayList<String[]> listNames=db.listNames(difficulty);
            for (int i = 0; i <listNames.size() ; i++) {
                adapter.add(listNames.get(i)[1]);
            }

            final ListView listView=(ListView)(findViewById(R.id.boardlist));
           listView.setAdapter(adapter);
           listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
                    //legger til navnet til brettet som en extra intent
                   Log.i(getLocalClassName(),"id: "+id+" "+adapterView.getItemAtPosition(id));
                   Intent intent1=getIntent();
                   intent1.putExtra("boardname",(String)adapterView.getItemAtPosition(id));

               }
           });




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void backButton(View v){
        finish();
    }

    public void launchBoard(View view){
        ArrayList<String> board;
        try {
            Intent intetnt=getIntent();
            DatabaseManager db = new DatabaseManager(getBaseContext());
            //hetner brettet fra databsen
            board = db.getBoard(difficulty, intetnt.getExtras().get("boardname").toString());
            int pointer=0;
            ArrayList<int []> data = new ArrayList<>();
            for (int y = 0; y <9 ; y++) {
                int[] row=new int[9];
                for (int x = 0; x <9 ; x++) {
                    row[x] = Integer.parseInt(board.get(pointer));
                    pointer++;
                }
                data.add(row);
            }
            //åpner brette
            Intent intent =new Intent(this,GridActivity.class);
            intent.putExtra("board",data);
            startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addBoard(View v){
        ArrayList<String> board;
        try {
            Intent intetnt=getIntent();
            int pointer=0;
            //åpner brette
            Intent intent =new Intent(this,GridActivity.class);
            intent.putExtra("newBorad",true);
            startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}