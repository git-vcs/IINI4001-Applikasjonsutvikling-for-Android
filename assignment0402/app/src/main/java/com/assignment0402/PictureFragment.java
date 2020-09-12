package com.assignment0402;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PictureFragment extends Fragment {

    TypedArray pictureName;
    Drawable currentPicture;
    ImageView bilde;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PictureFragment()  {}
    @Override
    public void onAttach(Activity activity) {
        Log.i(this.getClass().getSimpleName(), "onAttach: ");
        super.onAttach(activity);
    }

/*

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager=getFragmentManager();
        ButtonFragment buttTest=(ButtonFragment)fragmentManager.findFragmentById(R.id.main_layout_fragment_picture);

        //henter trasaskjon for å bytte ut fragment med et bilde
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_layout_fragment_picture,buttTest);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //This back stack is managed by the activity and allows the user to return to the previous fragment state, by pressing the Back button.
        //By calling addToBackStack(), the replace transaction is saved to the back stack so the user can reverse the transaction and bring back the previous fragment by pressing the Back button.
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }

 */

    @Override
    public void onDetach() {
        super.onDetach();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(), "onCreateView: ");

        //henter data fra strings.xml
        pictureName=getResources().obtainTypedArray(R.array.bildeFiler);
        //henter det første bidlet fra bildeFiler i string.xml
        currentPicture=pictureName.getDrawable(pictureName.length()-1); //note her antar vi at siste blde er det som vises før noe er valgt
        bilde=new ImageView(getContext());//laget et ImageView
        bilde.setImageDrawable(currentPicture); //setter bildet
        return bilde;
    }


    public void setPicture(int id){
        Log.i(this.getClass().getSimpleName(), "setPicture: ");
        bilde.setImageDrawable(pictureName.getDrawable(id)); //setter bildet

    }




}


