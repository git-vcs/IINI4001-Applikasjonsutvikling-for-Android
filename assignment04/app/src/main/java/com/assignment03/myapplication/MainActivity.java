package com.assignment03.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.assignment03.myapplication.ui.main.BildeListeFragment;

public class MainActivity extends AppCompatActivity {
    //for å kunne bytte ut fragementer under kjøring
   FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //Fragment bileListe=new Fragment();
        Fragment bilde=fragmentManager.findFragmentById(R.id.bilde_fragment);

        if(bilde!=null){
           // fragmentManager.beginTransaction().replace(R.id.main_fragment_bilde,bilde).commit();
        }





        if (savedInstanceState == null) {
            //getSupportFragmentManager().beginTransaction().add(R.id.container, BildeListeFragment.newInstance()).commitNow();
            //            getSupportFragmentManager().beginTransaction().add(R.id.container, bildefragment.newInstance()).commitNow();
        }
    }
}