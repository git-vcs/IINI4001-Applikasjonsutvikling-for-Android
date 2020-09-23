package com.assignment03.assignment05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "HttpActivity";
    private HttpWrapperThreaded network;
    final static String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private String userName="";
    private String kontoNummer="-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        network=new HttpWrapperThreaded(this,urlToServer);
    }

    //Method for showing response from HTTP server
    public void showResponse(String response){
        Log.w(this.getClass().getSimpleName()+" showResponse: ","Server responded with: " + response);
        Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
    }

    public void onStartButton(View v){
        Log.i(this.getClass().getSimpleName(), "onStartButton: ");
        EditText navn=(EditText)findViewById(R.id.editTextTextName);
        EditText tall=(EditText)findViewById(R.id.editTextNumber);
        userName=navn.getText().toString();
        kontoNummer=tall.getText().toString();
        Map<String,String> request=createRequestValues(new String[]{"navn","kortnummer"}, new String[]{userName, kontoNummer});
        Log.i(this.getClass().getSimpleName()," Registrasion Request: "+request);
        try {
            network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET,request);

        }catch (Exception e){
            Log.i(this.getClass().getSimpleName(),"onStartButton:Exception "+e.getMessage());

        }

    }

    public void onGjettButton(View v){
        EditText editTall=(EditText)findViewById(R.id.editTextTall);
        String gjettTall=editTall.getText().toString();
        Log.i(this.getClass().getSimpleName(), "onGjettButton: tall: "+gjettTall);
        try {
            Map<String,String> request=createRequestValues(new String[]{"tall"}, new String[]{gjettTall});
            network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET,request);

        }catch (Exception e){
            Log.i(this.getClass().getSimpleName(),"onStartButton:Exception "+e.getMessage());

        }
    }

    /* Lets make some parameters for the HTTP request */
    private Map<String,String> createRequestValues(String parameter[],String verdier[]){
        Map<String,String> valueList = new HashMap<String,String>();
        if(parameter.length==verdier.length){
            for (int i = 0; i <parameter.length ; i++) {
                valueList.put(parameter[i],verdier[i]);

            }
        }
        return valueList;
    }


}