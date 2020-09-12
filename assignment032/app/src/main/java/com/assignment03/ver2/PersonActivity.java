package com.assignment03.ver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {
    EditText navn;
    EditText dato;
    TextView title;

    int id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        title=(TextView)findViewById(R.id.textView);

        Log.i("AddButton-navn","onCreate");
        navn=(EditText) findViewById(R.id.editTextTextPersonName2);
        dato=(EditText) findViewById(R.id.editTextTextPersonName3);
        Intent person=getIntent();

        String navnString= person.getStringExtra("navn");
        String datoString=person.getStringExtra("dato");
        id=person.getIntExtra("id",-1);
        if(id!=-1){
            title.setText(R.string.editPerson);
        }

        if(navnString!=null){
            Log.i("AddButton-navn",String.valueOf(navnString.length()));
            navn.setText(navnString, TextView.BufferType.EDITABLE);


        }
        if(datoString!=null){
            dato.setText(datoString,TextView.BufferType.EDITABLE);
            Log.i("AddButton-dato",String.valueOf(datoString.length()));

        }
    }

    public void buttonSave(View v){
        Log.i("PersonActovoty","buttonSave");
        navn=(EditText) findViewById(R.id.editTextTextPersonName2);
        dato=(EditText) findViewById(R.id.editTextTextPersonName3);
        Intent person=getIntent();
        person.putExtra("navn",navn.getText().toString());
        person.putExtra("dato",dato.getText().toString());
        person.putExtra("buttoSave-id",id+"");
        setResult(Activity.RESULT_OK,person);
        finish();



    }


}