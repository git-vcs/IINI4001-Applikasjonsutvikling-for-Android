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


public class PictureListFragmentV2 extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PictureListFragmentV2() {}
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



        }

        /*

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.i(this.getClass().getSimpleName(), "onListItemClick: "+id);
        super.onListItemClick(l, v, position, id);


    }


         */






}
