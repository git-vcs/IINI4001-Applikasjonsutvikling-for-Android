package com.assignment03.ver2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Person> personer=new ArrayList<>();

   private void   generatePersonView(){
       ListView listView=(ListView) findViewById(R.id.listView);
       PersonAdapter adapter=new PersonAdapter(this, R.layout.personlist,personer);
       listView.setAdapter(adapter);
       listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent,View valgt, int posisjon, long id) {
               //denne onItemClick metoden blir lagt til på alle items i lista
               Log.i("Main-persinID", String.valueOf(id));
               Intent person=new Intent(valgt.getContext(),PersonActivity.class);
               person.putExtra("navn",personer.get(posisjon).getNavn());
               person.putExtra("dato",personer.get(posisjon).getDato());
               person.putExtra("id",posisjon);
               startActivityForResult(person,1);
           }
       });

    }




    public void buttonAdd(View v){
        Log.i("Main","addButton");
        //setContentView(R.layout.activity_person);
        Intent person=new Intent(this,PersonActivity.class);
        startActivityForResult(person,0);

    }


    public void avslutt(View v){
       finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main", "onCreate");

        //testdata
        personer.add(new Person("Fornavn1","11111111"));
        personer.add(new Person("Fornavn2","22222222"));
        personer.add(new Person("Fornavn3","44444444"));
        generatePersonView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Main", "onActivityResult: ");

        try {
            int tempID=data.getIntExtra("id", -1);

            // ny person blir registrert
            if(tempID==-1){
                personer.add(new Person(data.getStringExtra("navn"),data.getStringExtra("dato")));

            }else {
                //oppdatere en person
                personer.remove(tempID);
                personer.add(new Person(data.getStringExtra("navn"),data.getStringExtra("dato")));
            }

        }catch (Exception e){
            Log.i("onActivityResult", e.toString());
        }finally {
            generatePersonView();

        }
    }
}