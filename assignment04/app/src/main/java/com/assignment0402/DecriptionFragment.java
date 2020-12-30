package com.assignment0402;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DecriptionFragment extends Fragment {
    TextView currentDescription;
    TypedArray description;
    TextView pictureName;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DecriptionFragment()  {}
    @Override
    public void onAttach(Activity activity) {
        Log.i(this.getClass().getSimpleName(), "onAttach: ");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreateView: ");
        currentDescription=new TextView(getContext());
        //henter data fra strings.xml
        description=getResources().obtainTypedArray(R.array.bildeBeskrivelse);
        currentDescription.setText("Tykk på en ett av byggene for mere informasjon");

        return currentDescription;
    }


    public void setDescription(int id){
        Log.i(this.getClass().getSimpleName(), "setDescription: "+id);
       currentDescription.setText(description.getText(id));

    }

    public void next(Boolean next){
        Log.i(this.getClass().getSimpleName(), "next: ");

        if(next&&currentDescription.getId()<description.length()){
            currentDescription.setText(description.getText(currentDescription.getId()+1));
        }else if(!next&&currentDescription.getId()!=0){
            currentDescription.setText(description.getText(currentDescription.getId()-1));
        }

    }




}


