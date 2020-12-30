package com.assignment0402;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


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
       // currentPicture=pictureName.getDrawable(pictureName.length()-1); //note her antar vi at siste blde er det som vises før noe er valgt
        bilde=new ImageView(getContext());//laget et ImageView
        bilde.setImageDrawable(currentPicture); //setter bildet
        return bilde;
    }


    public void setPicture(int id){
        Log.i(this.getClass().getSimpleName(), "setPicture: ");
        bilde.setImageDrawable(pictureName.getDrawable(id)); //setter bildet

    }

}


