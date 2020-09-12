package com.assignment03.myapplication;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.time.Instant;

public class Bildefragment extends Fragment{
    private BildefragmentViewModel mViewModel;
    ImageView imageView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    public Bildefragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }






    // I denne metoden kan vi akkesere objekter i View og endre de
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(this.getClass().getSimpleName(),"onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        Context testContext=getContext();
        TextView test=new TextView(getContext());
        test.setText("Dette er plassen for bilder");
    }
@Override
    public void onDetach() {
        super.onDetach();
    }







    //dead code: koe som jeg ikke vet om jeg skal bruke enda










    // public static Bildefragment newInstance() {return new Bildefragment();}




    /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.getClass().getSimpleName(), "onCreate: ");


    }


 */



    //note vi kan ikke få tilgang til objekter i Views enda siden de ikke er laget

    /*
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // ListView can only be accessed here, not in onCreate()
        super.onCreateView(inflater,container, savedInstanceState);
        //dette vil feile(NULL) siden den ikke er lages enda
        //View view=inflater.inflate(R.layout.bilde_fragment,container,false);

        Log.i(this.getClass().getSimpleName(), "onCreateView: ");

        //return inflater.inflate(R.layout.bilde_fragment, container, false);
    }


     */




   /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BildefragmentViewModel.class);
        Log.i(this.getClass().getSimpleName(), "onActivityCreated: ");

        // TODO: Use the ViewModel
    }


 */


}