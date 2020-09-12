package com.assignment0402;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);



    }

    public void onClickTestButton(View v){
        Log.i(this.getClass().getSimpleName(), "onClickTestButton: ");

    }

    public void onClickNext(View v){
        Log.i(this.getClass().getSimpleName(), "onClickNext: ");


    }

    public void onClickBack(View v){
        Log.i(this.getClass().getSimpleName(), "onClickBack: ");


    }

}
