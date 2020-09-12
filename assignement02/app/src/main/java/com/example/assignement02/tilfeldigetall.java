package com.example.assignement02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.assignment02.R;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

public class tilfeldigetall extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_generator);
        Log.i("tilfeldigOncreate","Lasta");

        Random rndInt=new Random();
        int temp =rndInt.nextInt(100);
        Log.i("RND onCreate",temp+"");
        Intent result=new Intent();
        Log.i("RND_onCreate",result.getIntExtra("rnd",100)+"");
        result.putExtra("rnd",temp);
        Log.i("RND_onCreate plassert",result.getIntExtra("rnd",100)+"");
        this.setResult(Activity.RESULT_OK,result);


        finish();


    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("onOptionsItemSelected()",item.getTitle().toString());
        //noinspection SimplifiableIfStatement;
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */
        return super.onOptionsItemSelected(item);
    }

    public void genererTall(View view) {
        Random rndInt=new Random();
        ;
        Log.i("Button","Generer tall");
        Toast tall=Toast.makeText(this,"",Toast.LENGTH_SHORT);
        tall.cancel();
        tall.setText("Ditt tilfeldige tall er: "+rndInt.nextInt(100));

        //tall.show();
         finish();


    }


}
