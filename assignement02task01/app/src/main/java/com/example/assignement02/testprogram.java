package com.example.assignement02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment02task01.R;

import org.w3c.dom.Text;

public class testprogram extends AppCompatActivity {
    TextView tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tekst = (TextView) findViewById(R.id.textView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testprogram);
        Log.i("Main", "Lasta");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("onOptionsItemSelected()", item.getTitle().toString());
        //noinspection SimplifiableIfStatement;

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hentTall(View view) {
        Log.i("button", "Knappen i testprogram er trykker på");
        Intent intent = new Intent(this, tilfeldigetall.class);
        intent.putExtra("rnd",-1);
        startActivityForResult(intent, 2);

    }


    public void print(View view) {
        // Log.i("printtall",intent.getIntExtra("rnd",1)+"");

    }




    public static final int REQUEST_CODE = 1;


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "");
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

                String requiredValue = data.getStringExtra("key");
            }
        } catch (Exception ex) {
            Log.i("Exception", ex.toString());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "");
    }
}
