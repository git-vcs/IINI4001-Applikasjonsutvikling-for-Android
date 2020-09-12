package com.example.assignement02task2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class Oppgave_main extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_oppgave_main);
        Log.i("Main ","onCreate");
    }

    @Override
    protected void onResume() {
        Log.i("Main ","onResume");

        super.onResume();
    }

    public void buttonMultip(View view){
        Log.i("Main ","Button multi");
        Toast respons=new Toast(getApplicationContext());
        TextView textView1=(TextView) findViewById(R.id.textView3);
        TextView textView2=(TextView) findViewById(R.id.textView4);
        EditText editText=(EditText)findViewById(R.id.editTextTextPersonName2);
        int tall1, tall2, input;

        try {
            tall1=Integer.parseInt(textView1.getText().toString());
            tall2=Integer.parseInt(textView2.getText().toString());
            input=Integer.parseInt(editText.getText().toString());
            if(tall1*tall2==input){
                respons.setText(getResources().getString(getResources().getIdentifier("correct", "string", getPackageName())));
            }else {
                String tempString =getResources().getString(getResources().getIdentifier("wrong", "string", getPackageName()));
                tempString+=" "+(tall1*tall2);
                respons.setText(tempString);
            }
            EditText maxVerdi=(EditText) findViewById(R.id.editTextTextPersonName);
            maxVerdi.getText();
            Intent intent=new Intent(this,rnd.class);
            intent.putExtra("max",Integer.parseInt(String.valueOf(maxVerdi.getText())));
            startActivityForResult(intent,1);
        }catch (Exception e){
            respons.setText(e.toString());
        }
        respons.show();
    }

    public void buttonAdd(View view){
        Toast respons=new Toast(getApplicationContext());
        Log.i("Main","Button add");
        TextView textView1=(TextView) findViewById(R.id.textView3);
        TextView textView2=(TextView) findViewById(R.id.textView4);
        EditText editText=(EditText)findViewById(R.id.editTextTextPersonName2);
        int tall1, tall2, input;
        try {
            tall1=Integer.parseInt(textView1.getText().toString());
            tall2=Integer.parseInt(textView2.getText().toString());
            input=Integer.parseInt(editText.getText().toString());
            if(tall1+tall2==input){
                respons.setText(getResources().getString(getResources().getIdentifier("correct", "string", getPackageName())));
            }else {
                String tempString =getResources().getString(getResources().getIdentifier("wrong", "string", getPackageName()));
                tempString+=" "+(tall1+tall2);
                respons.setText(tempString);
            }


            Intent intent=new Intent(this,rnd.class);
            intent.putExtra("max",10);
            startActivityForResult(intent,1);
        }catch (Exception e){
            respons.setText(e.toString());
        }
        respons.show();

    }

    public void buttonNewTask(View view){
        try{
            EditText maxVerdi=(EditText) findViewById(R.id.editTextTextPersonName);
            maxVerdi.getText();
            Intent intent=new Intent(this,rnd.class);
            intent.putExtra("max",Integer.parseInt(String.valueOf(maxVerdi.getText())));
            startActivityForResult(intent,1);

        }catch (Exception e){

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView1=(TextView) findViewById(R.id.textView3);
        TextView textView2=(TextView) findViewById(R.id.textView4);
        textView1.setText(data.getExtras().get("rnd01").toString());
        textView2.setText(data.getExtras().get("rnd02").toString());

        super.onActivityResult(requestCode, resultCode, data);

    }


}
