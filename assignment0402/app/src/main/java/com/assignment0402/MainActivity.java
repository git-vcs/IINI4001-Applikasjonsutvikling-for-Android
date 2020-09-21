package com.assignment0402;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Objects;

public class MainActivity extends Activity {
    private String steder[];
    private FragmentTransaction ft;
    private DecriptionFragment decriptionFragment;
    private PictureFragment bilde;
    private FragmentManager fragmentManager;
    private PictureListFragmentV2 list;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreate: ");
        super.onCreate(savedInstanceState);
        Resources res=getResources();
        steder=res.getStringArray(R.array.bildeNavn);


        list=new PictureListFragmentV2();
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        initiserListeFragment();
        initierBildeFragment();
        initierBildeBeskrivelseFragment();
        setContentView(R.layout.main_layout);












    }

    public void onClickTestButton(View v){
        Log.i(this.getClass().getSimpleName(), "onClickTestButton: ");
        decriptionFragment.next(true);
    }

    public void onClickNext(View v){
        Log.i(this.getClass().getSimpleName(), "onClickNext: ");
        Fragment bilde =fragmentManager.findFragmentByTag("bilde");


    }

    public void onClickBack(View v){
        Log.i(this.getClass().getSimpleName(), "onClickBack: ");


    }

    public void initiserListeFragment(){
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,steder);
        Fragment listefragment=new Fragment();
        ListView listView=new ListView(getBaseContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(this.getClass().getSimpleName(), "onItemClick: "+i);
                decriptionFragment.setDescription(i);
                bilde.setPicture(i);

            }
        });



        


    }

    public void initierBildeFragment(){
        //oppset for PictureFragment
        bilde=new PictureFragment();
        fragmentTransaction.replace(R.id.main_layout_fragment_picture,bilde,"bilde");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();//todo sjekk om det er mulig å legge til flere ting samtidig

    }

    public void initierBildeBeskrivelseFragment(){
        //Oppset for DecriptionFragment
        fragmentTransaction=fragmentManager.beginTransaction();
        decriptionFragment=new DecriptionFragment();
        fragmentTransaction.replace(R.id.main_layout_fragment_description,decriptionFragment,"beskrivelse");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();//todo sjekk om det er mulig å legge til flere ting samtidig

    }

}
