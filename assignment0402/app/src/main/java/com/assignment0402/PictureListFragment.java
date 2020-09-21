package com.assignment0402;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;


import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.ListView;


public class PictureListFragment extends ListFragment {
    private DecriptionFragment decriptionFragment;
    private PictureFragment bilde;
    private FragmentManager fragmentManager;
    FragmentTransaction ft;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PictureListFragment() {}
    @Override
    public void onAttach(Activity activity) {
        Log.i(this.getClass().getSimpleName(), "onAttach: ");
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //laster inn data fra string.xml
        Log.i(this.getClass().getSimpleName(), "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        ConstraintLayout.LayoutParams params=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.FILL_PARENT, ConstraintLayout.LayoutParams.FILL_PARENT);
        Resources res=getResources();
        String pictureListNavn[]=res.getStringArray(R.array.bildeNavn);
        //Legger til navnene i lista
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,pictureListNavn));

        //oppset for PictureFragment
        fragmentManager=getFragmentManager();
        ft=fragmentManager.beginTransaction();
        bilde=new PictureFragment();
        ft.replace(R.id.main_layout_fragment_picture,bilde,"bilde");
        ft.addToBackStack(null);
        ft.commit();


        //Oppset for DecriptionFragment
        ft=fragmentManager.beginTransaction();
        decriptionFragment=new DecriptionFragment();
        ft.replace(R.id.main_layout_fragment_description,decriptionFragment);
        ft.addToBackStack("");
        ft.commit();

        }

    // metode for å gå fram eller tilbake i lista
    public void next(boolean action){
        Log.i(this.getClass().getSimpleName(), "next: ");

        //true=en fram; False= en tilbake
        decriptionFragment.next(action);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.i(this.getClass().getSimpleName(), "onListItemClick: "+id);
        super.onListItemClick(l, v, position, id);

        //Bytter bilde
        bilde.setPicture((int)id);
        //Bytter beskrivelse
        decriptionFragment.setDescription((int)id);

    }





}
