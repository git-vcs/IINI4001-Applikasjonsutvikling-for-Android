package com.assignment_06_frontend;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String ip ="192.168.122.1";
    final int PORTNR = 1250;
    Oppkobling oppkobling;
    String res="Syntax ERROR";
    Spinner alternativ;
    Handler update=new Handler();
    TextView resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alternativ=(Spinner) findViewById(R.id.opperator);
        ArrayAdapter<String> opperasjoner = new ArrayAdapter< String>(MainActivity.this,android.R.layout.simple_list_item_activated_1
        ,getResources().getStringArray(R.array.opperasjoner));
        opperasjoner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alternativ.setAdapter(opperasjoner);
        oppkobling=new Oppkobling(ip,PORTNR);
        oppkobling.start();
    }

        public void sendOppgave(View v){
        try {
            resultat=(TextView)findViewById(R.id.res);

            Double double_tall1=Double.parseDouble(((EditText)findViewById(R.id.tall1)).getText().toString());
            Double double_tall2=Double.parseDouble(((EditText)findViewById(R.id.tall2)).getText().toString());
            String opperator=alternativ.getItemAtPosition(alternativ.getSelectedItemPosition()).toString();
            Log.i("Oppgave: ",double_tall1+" "+opperator+" "+double_tall2);
            oppkobling.sendReq(double_tall1+" "+opperator+" "+double_tall2);

            update.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        resultat.setText(oppkobling.getRes());
                        Log.i("Res: ",oppkobling.getRes());
                    }catch (Exception e){
                        Log.i("Exseption i  ","Post");
                    }
                }
            });
        }catch (Exception e){
            Log.i(this.getClass().getSimpleName(),e.toString());
        }
    }


    @Override
    protected void onDestroy() {
        //lokker tilkobling til server når man lokker programmet
        oppkobling.close();
        super.onDestroy();

    }
}