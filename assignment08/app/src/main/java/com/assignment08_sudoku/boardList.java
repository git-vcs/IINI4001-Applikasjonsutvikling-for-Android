package com.assignment08_sudoku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Locale;

public class boardList extends AppCompatActivity {

    private int difficulty=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        try {
            //oppkobling til sql-database for å hente en lista og brettnavn
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
            //henter brettet fra databasen
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
            //åpner brettet
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
            //åpner brettet
            Intent intent =new Intent(this,GridActivity.class);
            intent.putExtra("newBoard",true);
            startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
        }



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

    public void settings(View v){
        startActivity(new Intent(this,settingsActivity.class));
    }
}